# Spring Boot Book Store API

A REST API built with Spring Boot for managing books with JWT authentication and role-based authorization.

## Features

- JWT Authentication
- Spring Security
- User Registration & Login
- Role-Based Authorization (USER / ADMIN)
- PostgreSQL Database
- Swagger OpenAPI Documentation
- CRUD Operations for Books
- Validation with Jakarta Validation
- Global Exception Handling
- DTO Pattern
- Spring Data JPA

## Technologies

- Java 26
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Swagger OpenAPI
- Maven

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|----------|----------|----------|
| POST | /auth/register | Register new user |
| POST | /auth/login | Login and get JWT token |

### Books

| Method | Endpoint | Description |
|----------|----------|----------|
| GET | /books | Get all books |
| GET | /books/{id} | Get book by ID |
| POST | /books | Create new book |
| PUT | /books/{id} | Update book |
| DELETE | /books/{id} | Delete book |

## Security

Protected endpoints require JWT token:

```http
Authorization: Bearer YOUR_TOKEN
```

## Database

PostgreSQL configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Swagger

After starting the application:

```text
http://localhost:8080/swagger-ui/index.html
```

## Running the Project

Clone repository:

```bash
git clone https://github.com/nefertiti200594-ui/backend-.git
```

Navigate to project:

```bash
cd backend-
```

Run application:

```bash
./mvnw spring-boot:run
```

## Author

Jeyhun Huseynov

MBA - Boston University

Backend Development Learning Project
