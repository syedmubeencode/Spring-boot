Based on your `ProductController`, here are the `curl` commands for each endpoint. 

Since your controller uses `@PreAuthorize`, I have included a placeholder for **Basic Authentication**. You will need to replace `admin_user:admin_password` and `regular_user:regular_password` with the actual credentials stored in your database or properties file.

---

### 1. Welcome (Unsecured)
This is a standard GET request that does not require authentication.
```bash
curl -X GET http://localhost:8080/products/welcome
```

### 2. Add New User
This is a POST request that sends a JSON body. Note that based on your controller code, this specific endpoint appears to be un-annotated with `@PreAuthorize`, but check your `SecurityConfig` to see if `/products/new` is permitted to all.
```bash
curl -X POST http://localhost:8080/products/new \
-H "Content-Type: application/json" \
-d '{
    "name": "Basant",
    "email": "basant@example.com",
    "password": "password123",
    "roles": "ROLE_ADMIN"
}'
```

### 3. Get All Products (Admin Only)
Requires `ROLE_ADMIN` authority.
```bash
curl -X GET http://localhost:8080/products/all \
-u admin_user:admin_password
```

### 4. Get Product by ID (User Only)
Requires `ROLE_USER` authority. Replace `{id}` with the numeric ID of the product (e.g., `1`).
```bash
curl -X GET http://localhost:8080/products/1 \
-u regular_user:regular_password
```

---

### Quick Troubleshooting Tips:
* **Port Number:** I used `8080` (Spring Boot default). If you changed `server.port` in your properties, update the curls accordingly.
* **Authentication:** If you are using JWT instead of Basic Auth, replace `-u user:pass` with `-H "Authorization: Bearer <your_token>"`.
* **Role Prefix:** Your code checks for `hasAuthority('ROLE_ADMIN')`. Ensure that when you save the user to the database via the `/new` endpoint, the role string actually includes the `ROLE_` prefix, as Spring Security's `hasAuthority` is exact-match.