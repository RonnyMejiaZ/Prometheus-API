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

## API Endpoints
Base URL when running locally: `http://localhost:8080`

### Authentication
- `POST /api/register` – create a new user (body: `RegisterRequest`)
- `POST /api/login` – authenticate and start a session (body: `LoginRequest`)

### Properties (`/api/propiedades`)
- `GET /api/propiedades` – list properties (supports `page`, `size`, `q` for pagination and search)
- `GET /api/propiedades/{id}` – fetch a single property by id
- `POST /api/propiedades` – create a property
- `PUT /api/propiedades/{id}` – update an existing property
- `DELETE /api/propiedades/{id}` – delete a property

### Tenants (`/api/inquilinos`)
- `GET /api/inquilinos` – list tenants (supports `page`, `size`, `q`)
- `GET /api/inquilinos/{id}` – fetch a tenant
- `POST /api/inquilinos` – create a tenant
- `PUT /api/inquilinos/{id}` – update a tenant
- `DELETE /api/inquilinos/{id}` – delete a tenant

### Rentals (`/api/alquileres`)
- `GET /api/alquileres` – list rentals (supports `page`, `size`, `q`)
- `GET /api/alquileres/{id}` – fetch a rental
- `POST /api/alquileres` – create a rental (accepts JSON or `multipart/form-data` when uploading contracts)
- `PUT /api/alquileres/{id}` – update a rental (JSON or multipart; replaces contract when a new file is attached)
- `DELETE /api/alquileres/{id}` – delete a rental (removes stored contract file)

### Payments (`/api/pagos`)
- `GET /api/pagos` – list payments (supports `page`, `size`, `q`)
- `GET /api/pagos/{id}` – fetch a payment
- `POST /api/pagos` – create a payment
- `PUT /api/pagos/{id}` – update a payment
- `DELETE /api/pagos/{id}` – delete a payment
