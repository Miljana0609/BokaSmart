# 📅 Booking & Todo API

A backend REST API built with Spring Boot that allows users to manage bookings and personal todo tasks.

The application includes JWT-based authentication, secure endpoints, and clean architecture following best practices.

---

## 🚀 Features

* 🔐 JWT Authentication (login & register)
* 📅 Booking management

  * Create bookings
  * View user bookings
  * Delete bookings
  * Todo management

  * Create todos
  * View todos
  * Toggle completion status
  * Delete todos
* 🛡️ Secure endpoints with Spring Security
* 📄 API documentation with Swagger
* 🧪 Unit testing with Mockito

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Security
* JWT (JSON Web Tokens)
* Spring Data JPA
* Hibernate
* Swagger / OpenAPI
* JUnit & Mockito

---

## 🔑 Authentication

This API uses JWT for authentication.

### 1. Register

POST `/api/auth/register`

### 2. Login

POST `/api/auth/login`

➡️ Returns a JWT token

### 3. Use Token

Include in request headers:

```
Authorization: Bearer <your_token>
```

---

## 📌 API Endpoints

### 🔐 Auth

* POST `/api/auth/register`
* POST `/api/auth/login`

---

### 📅 Bookings

* POST `/api/bookings` → Create booking
* GET `/api/bookings` → Get all bookings for logged-in user
* DELETE `/api/bookings/{id}` → Delete booking

---

### ✅ Todos

* POST `/api/todos` → Create todo
* GET `/api/todos` → Get all todos
* GET `/api/todos/{id}` → Toggle completed
* DELETE `/api/todos/{id
