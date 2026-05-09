Since your application is running on **port 8081**, here is the complete sequence of `curl` commands to test the entire lifecycle of the POS Receipt Engine.

### 1. Initialize a New Transaction

This creates a unique POS session. You **must** run this first to get a `transactionId`.

```bash
curl -X POST http://localhost:8081/api/cart/begin

```

* **Expected Output:** A JSON object. Copy the `transactionId` value (e.g., `"TXN-B4A1D2C8"`).

---

### 2. Product Lookup (SKU Check)

Verify if a product exists in your PostgreSQL `products` table before adding it.

```bash
curl -X GET http://localhost:8081/api/cart/lookup/SKU-001

```

---

### 3. Add Item to Cart

Replace `YOUR_TXN_ID` with the ID from Step 1. This updates the basket and recalculates the total.

```bash
curl -X POST http://localhost:8081/api/cart/YOUR_TXN_ID/add \
-H "Content-Type: application/json" \
-d '{
    "itemId": "SKU-001",
    "quantity": 1
}'

```

---

### 4. Process Payment & Generate PDF Receipt

This command finalizes the transaction, generates the **POSLOG XML**, stores it in the database, and returns the PDF file.

```bash
curl -X POST http://localhost:8081/api/cart/YOUR_TXN_ID/pay \
--output pos_receipt.pdf

```

* **Verification:** After running this, check your folder for `pos_receipt.pdf`.

---

### 5. Independent Receipt Engine Test (JSON Only)

If you just want to test the Velocity Template rendering with a raw JSON payload (bypassing the database), use this endpoint.

```bash
curl -X POST http://localhost:8081/api/receipt/generate \
-H "Content-Type: application/json" \
-d '{
    "transactionId": "TEST-123",
    "storeId": "HYD-TCS-01",
    "totalAmount": 100.00,
    "items": [
        {
            "itemId": "SKU-99",
            "description": "Manual Test Item",
            "quantity": 1,
            "price": 100.00
        }
    ]
}'

```

---

### Summary Checklist for Success:

1. **Port:** Ensure your `application.properties` specifies `server.port=8081`.
2. **Database:** Verify that `SKU-001` actually exists in your `products` table in pgAdmin.
3. **Templates:** Ensure `receipt_v1.vm` is located in `src/main/resources/templates/`.
4. **Static UI:** Access the interactive dashboard at `