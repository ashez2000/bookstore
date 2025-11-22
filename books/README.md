# BookStore (books service)

Books catalog and metadata service

### Endpoints

- GET /api/books
- GET /api/books/{id}
- POST /api/books
- PUT /api/books/{id}
- DELETE /api/books/{id}
- POST /api/books/{id}/reserve
- POST /api/books/{id}/release

### Build Docker Image

```bash
mvn compile jib:dockerBuild
```
