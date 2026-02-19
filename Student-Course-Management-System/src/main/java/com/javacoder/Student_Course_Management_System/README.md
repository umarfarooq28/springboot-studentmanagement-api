# Student Management System – Spring Boot

## Overview
This is a Spring Boot REST API project for managing students, courses, and enrollments with JWT-based authentication.  
The application supports secure CRUD operations and role-based access.

## Tech Stack
- Java
- Spring Boot
- Spring Security + JWT
- JPA / Hibernate
- MySQL
- Maven
- Postman

## Features
- User Registration and Login with JWT Authentication
- Student CRUD Operations
- Course CRUD Operations
- Student Enrollment Management
- Role-based Authorization
- RESTful APIs
- Exception Handling

## Project Structure
- Controller Layer – Handles API requests
- Service Layer – Business logic
- Repository Layer – Database interaction
- Entity Layer – JPA entities
- Security Layer – JWT + Spring Security

## How to Run the Project

1. Create MySQL database:
```sql
CREATE DATABASE studentdb;