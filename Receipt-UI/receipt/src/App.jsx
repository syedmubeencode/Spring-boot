import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { PosProvider } from './context/PosContext'; // Correct path
import HomePage from './pages/HomePage';             // Correct path
import PaymentPage from './pages/PaymentPage';       // Correct path
import CheckoutPage from './pages/CheckoutPage';     // Correct path

function App() {
  return (
    <PosProvider>
      <Router>
        <div className="min-h-screen bg-gray-50 text-gray-900">
          <nav className="bg-white border-b py-4 shadow-sm">
            <div className="max-w-7xl mx-auto px-6 flex justify-between items-center">
              <span className="font-black text-xl tracking-tight text-indigo-600 flex items-center gap-1">
                ⚡ FLASH POS SYSTEM
              </span>
              <span className="text-xs bg-gray-100 text-gray-600 px-3 py-1.5 rounded-full font-mono font-bold">
                API port: 8081
              </span>
            </div>
          </nav>
          
          <main className="py-4">
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/payment" element={<PaymentPage />} />
              <Route path="/checkout" element={<CheckoutPage />} />
            </Routes>
          </main>
        </div>
      </Router>
    </PosProvider>
  );
}

export default App;