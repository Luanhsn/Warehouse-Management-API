# Warehouse Management API

A RESTful API for managing warehouse inventory, built with Java and Spring Boot.

## Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- **Spring Security** with JWT Authentication
- **Spring Data JPA** + Hibernate
- **PostgreSQL**
- **Maven**
- **Swagger / OpenAPI**

## Features

- Product CRUD (Create, Read, Update, Delete)
- JWT-based Authentication (Register & Login)
- Input Validation
- Global Exception Handling
- API Documentation via Swagger UI

## Project Structure

```
src/main/java/com/luanhsn/warehouse_management_api/
├── config/         # Security, JWT Filter, JWT Service
├── controller/     # REST Controllers
├── dto/            # Request and Response DTOs
├── exception/      # Custom Exceptions and Global Handler
├── model/          # JPA Entities
├── repository/     # Spring Data JPA Repositories
└── service/        # Business Logic
```

## Getting Started

### Prerequisites

- Java 21
- PostgreSQL (or Docker)
- Maven

### Database Setup (Docker)

```bash
docker run --name warehouse-db \
  -e POSTGRES_USER=your_username \
  -e POSTGRES_PASSWORD=your_password \
  -e POSTGRES_DB=warehouse_db \
  -p 5433:5432 \
  -d postgres
```

### Configuration

Create `src/main/resources/application-local.properties`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run the Application

```bash
./mvnw spring-boot:run
```

## API Documentation

After starting the application, open Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| POST | `/api/products` | Create a new product |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

> All product endpoints require a valid JWT token in the `Authorization: Bearer <token>` header.

## Authentication Flow

1. Register: `POST /api/auth/register?username=user&password=pass`
2. Login: `POST /api/auth/login?username=user&password=pass`
3. Use the returned token in the `Authorization` header for all protected endpoints
