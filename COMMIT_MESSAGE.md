# Mensaje de Commit Recomendado

## Opción 1: Mensaje Completo y Detallado (Recomendado)

```
feat: Add REST API endpoints for Pagos and file upload utilities

- Add PagosApiServlet with full CRUD operations (GET, POST, PUT, DELETE)
- Implement pagination and search functionality in PagoRepository
- Add FileUtils utility class for file upload handling (contracts)
  - File validation (type and size limits)
  - Unique filename generation using UUID
  - Support for PDF, DOCX, JPG, PNG file types
- Update AlquilerApiServlet with wildcard URL pattern support
- Update Pago and Alquiler models to support new API features
- Remove redundant doOptions methods (CORS handled by CorsFilter)

API Endpoints:
- GET    /api/pagos        - List pagos with pagination
- GET    /api/pagos/{id}   - Get single pago
- POST   /api/pagos        - Create new pago
- PUT    /api/pagos/{id}   - Update pago
- DELETE /api/pagos/{id}   - Delete pago
```

## Opción 2: Mensaje Más Conciso

```
feat: Add Pagos REST API and file upload utilities

- Add PagosApiServlet with CRUD operations
- Add FileUtils for contract file management
- Improve PagoRepository with search and pagination
- Update AlquilerApiServlet with wildcard support
- Update Pago and Alquiler models
```

## Opción 3: Mensaje Muy Breve

```
feat: Add Pagos API endpoints and file upload support
```

## Guía de Uso

1. **Copia el mensaje** de la opción que prefieras
2. **En tu IDE**, pega el mensaje en el campo "Message"
3. **Asegúrate de que todos los archivos estén seleccionados** para el commit
4. **Haz click en "Commit"**

---

## Explicación del Formato

- **`feat:`** - Indica que es una nueva funcionalidad (feature)
- **Título**: Primera línea, máximo 72 caracteres, describe el cambio principal
- **Cuerpo**: Lista detallada de cambios, cada línea describe un cambio específico
- **Bullet points**: Usa `-` para listar cambios individuales

### Convenciones de Commits (Conventional Commits)

- `feat:` - Nueva funcionalidad
- `fix:` - Corrección de bugs
- `refactor:` - Refactorización de código
- `docs:` - Cambios en documentación
- `style:` - Cambios de formato (espacios, comas, etc.)
- `test:` - Agregar o modificar tests
- `chore:` - Cambios en configuración, build, etc.

