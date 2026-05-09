import React from 'react';
import { usePos } from '../context/PosContext';
import { useNavigate } from 'react-router-dom';
import { CreditCard, Banknote, Globe, ArrowLeft, CheckCircle2 } from 'lucide-react';

export default function PaymentPage() {
  const { paymentMethod, setPaymentMethod, getCartTotal, transactionId } = usePos();
  const navigate = useNavigate();

  const handleSelectPayment = (method) => {
    setPaymentMethod(method);
  };

  const proceedToCheckout = () => {
    if (!paymentMethod) return;
    navigate('/checkout');
  };

  return (
    <div className="max-w-xl mx-auto p-6 mt-10 bg-white border border-gray-200 rounded-2xl shadow-xl">
      <button onClick={() => navigate('/')} className="flex items-center gap-1 text-gray-500 hover:text-gray-800 mb-6 text-sm">
        <ArrowLeft size={16}/> Back to Cart
      </button>

      <h1 className="text-2xl font-bold mb-2">Tender / Payment Method</h1>
      <p className="text-gray-500 mb-6">Select preferred payment option for session <code className="bg-gray-100 px-1 py-0.5 rounded font-bold text-xs">{transactionId}</code></p>

      <div className="bg-indigo-50 p-4 rounded-xl flex justify-between items-center mb-6 border border-indigo-100">
        <span className="text-indigo-900 font-semibold">Total Amount Due:</span>
        <span className="text-2xl font-black text-indigo-700">${getCartTotal().toFixed(2)}</span>
      </div>

      <div className="space-y-4">
        {/* Cash Option */}
        <button 
          onClick={() => handleSelectPayment('CASH')}
          className={`w-full p-5 border-2 rounded-xl flex items-center justify-between transition ${
            paymentMethod === 'CASH' ? 'border-green-600 bg-green-50/50' : 'border-gray-200 hover:border-gray-300'
          }`}
        >
          <div className="flex items-center gap-4">
            <div className="p-3 bg-green-100 text-green-700 rounded-lg"><Banknote size={24}/></div>
            <div className="text-left">
              <p className="font-bold text-gray-800">Cash Payment</p>
              <p className="text-xs text-gray-400">Tender and pay directly in store</p>
            </div>
          </div>
          {paymentMethod === 'CASH' && <CheckCircle2 className="text-green-600" />}
        </button>

        {/* Online / UPI Option */}
        <button 
          onClick={() => handleSelectPayment('ONLINE')}
          className={`w-full p-5 border-2 rounded-xl flex items-center justify-between transition ${
            paymentMethod === 'ONLINE' ? 'border-indigo-600 bg-indigo-50/50' : 'border-gray-200 hover:border-gray-300'
          }`}
        >
          <div className="flex items-center gap-4">
            <div className="p-3 bg-indigo-100 text-indigo-700 rounded-lg"><Globe size={24}/></div>
            <div className="text-left">
              <p className="font-bold text-gray-800">Online Payment / UPI</p>
              <p className="text-xs text-gray-400">Pay using QR, Digital Wallet, or Card Online</p>
            </div>
          </div>
          {paymentMethod === 'ONLINE' && <CheckCircle2 className="text-indigo-600" />}
        </button>

        {/* Card Terminal Option */}
        <button 
          onClick={() => handleSelectPayment('CARD')}
          className={`w-full p-5 border-2 rounded-xl flex items-center justify-between transition ${
            paymentMethod === 'CARD' ? 'border-amber-600 bg-amber-50/50' : 'border-gray-200 hover:border-gray-300'
          }`}
        >
          <div className="flex items-center gap-4">
            <div className="p-3 bg-amber-100 text-amber-700 rounded-lg"><CreditCard size={24}/></div>
            <div className="text-left">
              <p className="font-bold text-gray-800">EMV Card Reader</p>
              <p className="text-xs text-gray-400">Swipe, Dip or Tap terminal card</p>
            </div>
          </div>
          {paymentMethod === 'CARD' && <CheckCircle2 className="text-amber-600" />}
        </button>
      </div>

      <button 
        disabled={!paymentMethod}
        onClick={proceedToCheckout}
        className={`w-full mt-8 py-4 rounded-xl font-bold transition text-center block ${
          paymentMethod ? 'bg-indigo-600 text-white hover:bg-indigo-700' : 'bg-gray-300 text-gray-500 cursor-not-allowed'
        }`}
      >
        Proceed to Review & Checkout
      </button>
    </div>
  );
}