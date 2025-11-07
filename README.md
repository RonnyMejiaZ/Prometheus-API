# Prometheus Rental API

This project exposes a RESTful API for managing rental operations (properties, tenants, leases, payments, and users). It replaces the former JSP UI with JSON endpoints that a modern frontend such as React can consume.

## Features
- CRUD endpoints for properties, tenants, leases (alquileres), and payments
- Registration, login, and logout flows with session handling
- Consistent `ApiResponse` wrapper for success and error payloads
- Pagination and search helpers in repositories
- Centralized JSON serialization via `JsonUtils`

## How to Run Locally
```bash
mvn clean package
mvn tomcat7:run
```
Then access the API at `http://localhost:8080/api/...` (for example `http://localhost:8080/api/propiedades`).

## Next Steps
Pair this backend with the React client or any other frontend that calls the API endpoints using `fetch` or a similar HTTP client.
