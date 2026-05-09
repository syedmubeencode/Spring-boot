import React, { useState } from 'react';
import { usePos } from '../context/PosContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Receipt, FileText, CheckCircle, ArrowLeft } from 'lucide-react';

export default function CheckoutPage() {
  const { transactionId, cart, paymentMethod, getCartTotal, initTransaction } = usePos();
  const [isProcessing, setIsProcessing] = useState(false);
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleFinalizeAndPrint = async () => {
    if (!transactionId) return;
    try {
      setIsProcessing(true);

      // Call API End-Point (Step 4 of Curl sequence)
      const payUrl = `http://localhost:8081/api/cart/${transactionId}/pay`;
      
      const response = await axios({
        method: 'POST',
        url: payUrl,
        responseType: 'blob', // Critical: This ensures Axios treats response as raw binary file data
      });

      // 1. Convert byte stream into a local virtual object URL
      const blob = new Blob([response.data], { type: 'application/pdf' });
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = `receipt_${transactionId}.pdf`;
      
      // 2. Programmatically click the virtual link to initiate download
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);

      setSuccess(true);
    } catch (err) {
      console.error("Payment & receipt processing error:", err);
      alert("Error generating your receipts on the engine.");
    } finally {
      setIsProcessing(false);
    }
  };

  const handleStartOver = () => {
    initTransaction(); // Clear context and starts fresh session
    navigate('/');
  };

  return (
    <div className="max-w-lg mx-auto p-6 mt-10">
      <button 
        onClick={() => navigate('/payment')} 
        disabled={success}
        className="flex items-center gap-1 text-gray-500 hover:text-gray-800 mb-6 text-sm disabled:opacity-50"
      >
        <ArrowLeft size={16}/> Back to Payment
      </button>

      {/* Styled Paper Receipt UI Representation */}
      <div className="bg-white border-2 border-dashed border-gray-300 p-6 rounded-2xl shadow-xl space-y-6 relative overflow-hidden">
        {success && (
          <div className="absolute top-0 left-0 w-full bg-green-500 text-white py-1 text-center text-xs font-bold flex items-center justify-center gap-1">
            <CheckCircle size={14}/> TRANSACTION APPROVED & LOGGED
          </div>
        )}

        <div className="text-center pt-2">
          <h2 className="text-xl font-mono font-black tracking-widest text-gray-800">HYD CENTRAL POS</h2>
          <p className="text-xs font-mono text-gray-500">Store Unit: HYD-CENTRAL-01</p>
          <p className="text-xs font-mono text-gray-500">POS-No: Reg-404</p>
        </div>

        <div className="border-b border-dashed border-gray-300 pb-4 text-xs font-mono text-gray-600 space-y-1">
          <p>Tx ID: {transactionId}</p>
          <p>Date: {new Date().toLocaleDateString()} {new Date().toLocaleTimeString()}</p>
          <p>Tender Mode: <strong className="text-indigo-600">{paymentMethod}</strong></p>
        </div>

        {/* Receipt Line Items */}
        <div className="space-y-3 font-mono text-sm border-b border-dashed border-gray-300 pb-4">
          {cart.map((item) => (
            <div key={item.itemId} className="flex justify-between">
              <div className="pr-4">
                <p className="text-gray-800 font-semibold">{item.description}</p>
                <p className="text-xs text-gray-500">{item.quantity} x ${item.price.toFixed(2)}</p>
              </div>
              <span className="font-bold text-gray-700">${(item.quantity * item.price).toFixed(2)}</span>
            </div>
          ))}
        </div>

        {/* Receipt Totals */}
        <div className="space-y-1 font-mono text-sm">
          <div className="flex justify-between text-gray-600">
            <span>Sub-Total</span>
            <span>${getCartTotal().toFixed(2)}</span>
          </div>
          <div className="flex justify-between text-gray-600">
            <span>GST / VAT (0%)</span>
            <span>$0.00</span>
          </div>
          <div className="flex justify-between text-lg font-bold border-t border-dashed border-gray-300 pt-3">
            <span>TOTAL PAID</span>
            <span>${getCartTotal().toFixed(2)}</span>
          </div>
        </div>

        <div className="text-center text-xs text-gray-400 font-mono mt-4">
          <p>Thank you for shopping with us!</p>
          <p>Velocity Template Rendering V1</p>
        </div>
      </div>

      {/* Primary Action Buttons */}
      <div className="mt-8 space-y-3">
        {!success ? (
          <button
            onClick={handleFinalizeAndPrint}
            disabled={isProcessing}
            className={`w-full py-4 rounded-xl font-bold flex justify-center items-center gap-2 text-white shadow-lg transition ${
              isProcessing ? 'bg-indigo-400 cursor-wait' : 'bg-indigo-600 hover:bg-indigo-700'
            }`}
          >
            <FileText size={20}/> 
            {isProcessing ? 'Calling API & Generating...' : 'Finish & Generate PDF Receipt'}
          </button>
        ) : (
          <button
            onClick={handleStartOver}
            className="w-full py-4 rounded-xl font-bold flex justify-center items-center gap-2 text-white bg-green-600 hover:bg-green-700 shadow-lg transition"
          >
            <Receipt size={20}/> Start New Transaction
          </button>
        )}
      </div>
    </div>
  );
}