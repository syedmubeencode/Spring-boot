import React, { useState, useEffect } from 'react';
import { usePos } from '../context/PosContext';
import { useNavigate } from 'react-router-dom';
import { ShoppingCart, Plus, Minus, Trash2, Play } from 'lucide-react';

const STATIC_CATALOG = [
  { itemId: 'SKU-001', description: 'Product SKU-001', price: 120.00 },
  { itemId: 'SKU-101', description: 'Java Microservices Book', price: 400.00 },
  { itemId: 'SKU-102', description: 'Coffee Bean Bag', price: 50.50 }
];

export default function HomePage() {
  const { transactionId, initTransaction, cart, addToCartAPI, removeItemLocal, getCartTotal, loading } = usePos();
  const [manualSku, setManualSku] = useState('');
  const navigate = useNavigate();

  // Initialize a transaction automatically if none exists
  useEffect(() => {
    if (!transactionId) {
      initTransaction();
    }
  }, [transactionId]);

  const handleManualAdd = (e) => {
    e.preventDefault();
    if (!manualSku.trim()) return;
    addToCartAPI(manualSku.trim(), 1);
    setManualSku('');
  };

  return (
    <div className="p-6 max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-6">
      {/* Catalog & Manual Input Section */}
      <div className="md:col-span-2 space-y-6">
        <header className="bg-gray-100 p-4 rounded-xl flex justify-between items-center">
          <div>
            <h1 className="text-2xl font-bold text-gray-800">Terminal POS</h1>
            <p className="text-sm text-gray-500">Session ID: <strong className="text-indigo-600">{transactionId || "Initializing..."}</strong></p>
          </div>
          <button 
            onClick={initTransaction} 
            className="px-4 py-2 bg-indigo-100 text-indigo-700 rounded-lg hover:bg-indigo-200 text-sm font-medium"
          >
            New Session
          </button>
        </header>

        {/* Manual SKU Search Bar */}
        <form onSubmit={handleManualAdd} className="flex gap-2">
          <input 
            type="text" 
            placeholder="Scan or enter SKU (e.g. SKU-101)..."
            value={manualSku}
            onChange={(e) => setManualSku(e.target.value)}
            className="flex-grow p-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
          <button type="submit" className="bg-indigo-600 text-white px-6 py-3 rounded-lg hover:bg-indigo-700 font-semibold">
            Add SKU
          </button>
        </form>

        {/* Quick Select Grid */}
        <div>
          <h2 className="text-lg font-bold mb-4 text-gray-700">Quick-Add Database Catalog</h2>
          <div className="grid grid-cols-2 sm:grid-cols-3 gap-4">
            {STATIC_CATALOG.map((prod) => (
              <button 
                key={prod.itemId}
                onClick={() => addToCartAPI(prod.itemId, 1)}
                className="p-4 border rounded-xl hover:border-indigo-500 hover:shadow-md transition text-left flex flex-col justify-between bg-white h-32"
              >
                <span className="text-xs text-gray-400 font-mono">{prod.itemId}</span>
                <span className="font-bold text-gray-800 line-clamp-2">{prod.description}</span>
                <span className="text-indigo-600 font-bold">${prod.price.toFixed(2)}</span>
              </button>
            ))}
          </div>
        </div>
      </div>

      {/* Cart Terminal Panel */}
      <div className="bg-white border border-gray-200 rounded-2xl shadow-xl flex flex-col h-[600px]">
        <div className="p-4 border-b flex justify-between items-center bg-gray-50 rounded-t-2xl">
          <span className="font-bold flex items-center gap-2"><ShoppingCart size={20}/> Active Cart</span>
          <span className="bg-indigo-100 text-indigo-800 text-xs px-2.5 py-1 rounded-full font-bold">{cart.length} items</span>
        </div>

        <div className="flex-grow overflow-y-auto p-4 space-y-4">
          {cart.length === 0 ? (
            <div className="h-full flex flex-col items-center justify-center text-gray-400">
              <ShoppingCart size={48} className="mb-2 opacity-50"/>
              <p>Cart is currently empty.</p>
            </div>
          ) : (
            cart.map((item) => (
              <div key={item.itemId} className="flex justify-between items-center p-3 border rounded-lg bg-gray-50">
                <div className="flex-grow pr-2">
                  <h4 className="font-semibold text-gray-800 text-sm line-clamp-1">{item.description}</h4>
                  <p className="text-xs text-gray-500">${item.price.toFixed(2)} x {item.quantity}</p>
                </div>
                <div className="flex items-center gap-2">
                  <button onClick={() => addToCartAPI(item.itemId, -1)} className="p-1 hover:bg-gray-200 rounded text-gray-600"><Minus size={16}/></button>
                  <span className="font-bold text-sm w-4 text-center">{item.quantity}</span>
                  <button onClick={() => addToCartAPI(item.itemId, 1)} className="p-1 hover:bg-gray-200 rounded text-gray-600"><Plus size={16}/></button>
                  <button onClick={() => removeItemLocal(item.itemId)} className="p-1 text-red-500 hover:bg-red-50 rounded ml-2"><Trash2 size={16}/></button>
                </div>
              </div>
            ))
          )}
        </div>

        <div className="p-4 border-t bg-gray-50 rounded-b-2xl">
          <div className="flex justify-between font-bold text-lg mb-4 text-gray-800">
            <span>Subtotal:</span>
            <span>${getCartTotal().toFixed(2)}</span>
          </div>
          <button 
            disabled={cart.length === 0 || loading}
            onClick={() => navigate('/payment')}
            className={`w-full py-4 rounded-xl flex justify-center items-center gap-2 font-bold transition ${
              cart.length === 0 ? 'bg-gray-300 cursor-not-allowed text-gray-500' : 'bg-green-600 text-white hover:bg-green-700 shadow-md'
            }`}
          >
            Proceed to Payment <Play size={18}/>
          </button>
        </div>
      </div>
    </div>
  );
}