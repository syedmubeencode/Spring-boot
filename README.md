# 🌬️ Gzip Compression in Spring Boot

**Gzip** is a widely used compression technique that helps improve performance by reducing the size of data transmitted over the network.

---

## 🛠️ How It Works

When a Spring Boot application needs to send a large amount of data (such as JSON responses), **Gzip** compresses the data before sending it to the client. This leads to:

- Smaller payload size  
- Lower network latency  
- Faster response time

The client (browser or API consumer) automatically decompresses the response and renders it as expected.

---

## ✅ How to Enable Gzip in Spring Boot

Enabling Gzip in Spring Boot is straightforward. You just need to add a few properties in your `application.properties` file:

```properties
# Enable Gzip compression
server.compression.enabled=true

# Minimum response size to trigger compression
server.compression.min-response-size=1024

# MIME types to compress (add more as needed)
server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain
