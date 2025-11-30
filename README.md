# 🧾 Spring Boot Receipt Engine (Velocity + XML)

A simple Spring Boot service that converts POS XML data into a formatted text receipt using **Apache Velocity** and **Jackson XML**.

---

## 🔗 H2 Database Console

You can access the H2 database console using:

👉 **http://localhost:8080/h2-console/**

---

## 🗣️ How to Explain This Project in an Interview (Short Version)

“I built a Spring Boot–based receipt engine where a REST API accepts POS transaction data in XML format.  
The XML is converted into a Java model using Jackson XML, then I use **Apache Velocity** as a template engine.

The templates are written in Velocity Template Language (VTL) and stored on the classpath.  
At runtime, I load the correct template, inject the transaction object into a Velocity context, and generate a **plain text receipt string** as the API response.

I also added a **global exception handler** to return clean JSON errors if parsing or templating fails, making the service production-friendly and debuggable.”

---

# 🛠️ What This App Does

This project demonstrates how to build a modern Spring Boot application that:

- ✔️ Exposes a **Receipt REST API**  
- ✔️ Accepts **POS XML data** as input  
- ✔️ Converts XML → Java object using **Jackson XML**  
- ✔️ Reads a **Velocity Template (VTL)** from classpath  
- ✔️ Generates and returns a **text receipt** string  
- ✔️ Handles errors with a **global exception handler**

---

## 1️⃣ Project Idea (Simple Terms)

### 🔄 Flow of the Application

1. Client sends **XML transaction data** to  
   `POST /api/receipt`
2. The API parses the XML into a **Transaction Java object**
3. Based on the request, the app loads the correct **Velocity template**
4. `$txn` object is passed into the template
5. Velocity generates a **plain text receipt**
6. API returns the final receipt string

---

## 🚀 Features You Can Add

If you need, I can also generate code for:

- ✅ Swagger/OpenAPI documentation  
- ✅ Multiple templates (compact, long, promo, return-receipt)  
- ✅ Database logging (store receipts in DB)  
- ✅ Input validation + error handling  
- ✅ WAR packaging for external Tomcat  
- ✅ Simple UI to upload XML & download receipt  
- ✅ Logging + performance profiling  

---

## 📄 Velocity Template Example (`receipt.vm`)

Your template can use `$txn` fields like:

