import React, { createContext, useContext, useState } from 'react';
import axios from 'axios';

const PosContext = createContext();

const API_BASE = 'http://localhost:8081/api';

export const PosProvider = ({ children }) => {
  const [transactionId, setTransactionId] = useState(null);
  const [cart, setCart] = useState([]);
  const [paymentMethod, setPaymentMethod] = useState('');
  const [receiptData, setReceiptData] = useState(null);
  const [loading, setLoading] = useState(false);

  // 1. Initialize POS Session
  const initTransaction = async () => {
    try {
      setLoading(true);
      const res = await axios.post(`${API_BASE}/cart/begin`);
      // Assuming res.data returns { transactionId: "TXN-XXX" }
      const txnId = res.data.transactionId || res.data; 
      setTransactionId(txnId);
      setCart([]);
      setReceiptData(null);
      return txnId;
    } catch (err) {
      console.error("Error starting transaction:", err);
      alert("Failed to initialize POS transaction.");
    } finally {
      setLoading(false);
    }
  };

  // 2. Add / Update Item Quantities
  const addToCartAPI = async (sku, quantity = 1) => {
    if (!transactionId) {
      alert("No active session! Initializing now...");
      return;
    }

    try {
      setLoading(true);
      // First, lookup the item to verify it exists
      const lookupRes = await axios.get(`${API_BASE}/cart/lookup/${sku}`);
      const product = lookupRes.data; // e.g. { itemId: "SKU-001", description: "...", price: 100 }

      // Call the add to cart backend API
      await axios.post(`${API_BASE}/cart/${transactionId}/add`, {
        itemId: sku,
        quantity: quantity
      });

      // Update local React UI cart state
      setCart((prevCart) => {
        const existingIdx = prevCart.findIndex(item => item.itemId === sku);
        if (existingIdx > -1) {
          const updated = [...prevCart];
          updated[existingIdx].quantity += quantity;
          if (updated[existingIdx].quantity <= 0) {
            return updated.filter(item => item.itemId !== sku);
          }
          return updated;
        } else {
          return [...prevCart, { ...product, quantity }];
        }
      });

    } catch (err) {
      console.error("Error adding product to backend:", err);
      alert("Item SKU check failed or couldn't add to database.");
    } finally {
      setLoading(false);
    }
  };

  // 3. Remove/Void completely
  const removeItemLocal = (sku) => {
    // Local POS client-side fast-void
    setCart(prev => prev.filter(item => item.itemId !== sku));
  };

  // Calculates Cart Total
  const getCartTotal = () => {
    return cart.reduce((acc, item) => acc + (item.price * item.quantity), 0);
  };

  return (
    <PosContext.Provider value={{
      transactionId,
      setTransactionId,
      cart,
      setCart,
      paymentMethod,
      setPaymentMethod,
      receiptData,
      setReceiptData,
      loading,
      initTransaction,
      addToCartAPI,
      removeItemLocal,
      getCartTotal
    }}>
      {children}
    </PosContext.Provider>
  );
};

export const usePos = () => useContext(PosContext);