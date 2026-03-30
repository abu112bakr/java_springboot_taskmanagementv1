🔐 Secure Task Management Application

Spring Boot • Spring Security •JWT Token• PostgreSQL

⸻

📌 Project Status & Learning Roadmap

✅ Accomplished So Far

	•	Started from: Task Management CLI using core JAVA OOP

	•	Started: Task Management system using Spring Boot & JAVA

	•	Implemented database persistence using PostgreSQL.

	•	Created Users & ensured CRUD operations performed by users.

	•	Users can create, update, and delete tasks, with ownership validation (only task creators can modify their tasks).

	•	Ensured secure and user-specific access to all task operations.

	•	Implemented user registration and login with Spring Security and JWT-based authentication.

	•	Implemented OAuth2 to login using Google & GitHub.

	•	Custom JPQL query implementation using '@Query' annotation.

	•	Tasks include automatic timestamps (createdAt, updatedAt) and system-generated taskId.

	•	Liquibase: Implemented database versioning and migration management.

	•	Swagger Integration: Added API documentation for easier testing and visualization.

	•	Implemented SendGrid and SimpleMailMessage for email service.

	•	Integrated OpenWeather API to fetch weather data for task context.

	•	Integrated Gemini LLM API to generate and send AI-powered emails.

	•	Implemented application monitoring using Prometheus (metrics collection), Loki (log aggregation), and Grafana (visualization dashboard).

	•	Application logs are written to a dedicated logs/ folder and shipped to Loki via Promtail for real-time log monitoring in Grafana.

	•	Automated database backup using a scheduled backup.sh script, storing timestamped SQL dumps with automatic cleanup of old backups.

🔜 Future Enhancements

	1.	Role-Based Access Control (RBAC — introduce Admin, Manager, and User roles with fine-grained permissions)

	2.	Task Notification System (automatically notify users via email when a task is assigned, updated, or approaching its deadline)

	3.	CI/CD Pipeline Integration (GitHub Actions — automate build, test, and deployment on every push)


⸻

📌 Project Overview

A Task Management System built with Spring Boot (version 3.5.10), evolving from a core Java CLI application into a full-stack backend system.

Users can create, update, and delete tasks securely, with ownership validation ensuring only task creators can modify their own tasks.

The project implements JWT-based authentication, OAuth2 login via Google and GitHub, and AI-powered features including email generation via Gemini LLM and weather-based task context via OpenWeather API.

It integrates a full observability stack using Prometheus for metrics collection, Loki and Promtail for log aggregation, and Grafana for real-time visualization — with application logs persisted to a dedicated logs/ folder.

Database versioning is managed via Liquibase, and data persistence is handled using PostgreSQL running in Docker with automated backup support.

This README is updated regularly to track learning and development progress.

⸻

## 🛠 Features

### Authentication & Security
- User registration and login with Spring Security and JWT-based authentication.
- OAuth2 login via Google and GitHub.
- Secure, user-specific access to all task operations.

### Task Management
- Create, update, and delete tasks with ownership validation (only task creators can modify their tasks).
- Automatic timestamps (`createdAt`, `updatedAt`) and system-generated `taskId`.
- Custom JPQL queries using `@Query` annotation for flexible task filtering.

### AI & Integrations
- Gemini LLM API integration for AI-generated email content.
- OpenWeather API integration for weather-based task context.
- Email delivery via JavaMailSender (SMTP) and SendGrid.

### Database & Migrations
- PostgreSQL for data persistence, running in Docker.
- Liquibase for database versioning and migration management.
- Automated database backups via a scheduled `backup.sh` script.

### Observability & Monitoring
- Prometheus for application metrics collection.
- Loki and Promtail for log aggregation and shipping.
- Grafana for real-time metrics and log visualization.
- Application logs persisted to a dedicated `logs/` folder.

### Developer Experience
- Swagger UI for API documentation and testing.
- Docker Compose for containerized local development.
- H2 in-memory database support for lightweight testing.


⸻

## 🔐 Authentication Flow (JWT)

```mermaid
sequenceDiagram
    participant User
    participant Backend_API

    User->>Backend_API: Register (POST /register)
    Backend_API-->>User: Registration Success

    User->>Backend_API: Login (POST /login)
    Backend_API-->>User: JWT Token Issued

    User->>Backend_API: API Request (Bearer Token)
    Backend_API-->>User: Authorized Response
```

⸻	

	1.	User registers via /register.
	2.	User logs in via /login and receives a JWT token.
	3.	For all protected endpoints, the JWT token must be sent in the Authorization header as Bearer <token>.
	4.	Backend validates the token and allows authorized access.

📄 User Authentication

Task JSON Structure

Register a User

POST http://localhost:8080/register

{

    "id": 7,

    "username": "User7",

    "password": "u@123",

    "email": null,

    "provider": "null",

    "providerId": "null"
}


Log in a User

Post http://localhost:8080/loogin	

{

    "username": "cod",

    "password": "c@123"

}

Response: JWT token

Example: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjb2QiLCJpYXQiOjE3NzE0MTU1MTksImV4cCI6MTc3MTQxNzMxOX0.JIzf-KOWJ3nVKjE90RRQ_w9jfdkfglytBDl4tFewoD8

After login, select Auth Type: Bearer Token in your API client and paste the JWT token to authorize requests.

## 📊 Task CRUD Flow Diagram

```mermaid
sequenceDiagram
    participant User
    participant TaskController
    participant TaskService
    participant TaskRepository
    participant Database

    User->>TaskController: Create / Update / Delete Task
    TaskController->>TaskService: Validate Request + Ownership
    TaskService->>TaskRepository: Save / Update / Delete
    TaskRepository->>Database: Execute Query
    Database-->>TaskRepository: Result
    TaskRepository-->>TaskService: Operation Status
    TaskService-->>TaskController: Processed Response
    TaskController-->>User: Task Response (JSON)
```

---

## 🔐 Security & Validation

- All endpoints are secured using **JWT Authentication**
- Ownership validation is performed before update or delete operations
- Architectural flow:

```
User → Controller → Service → Repository → Database → Response
```

📄 Task Management API

Create a Task

Post a task

POST http://localhost:8080/task

{

  "taskName": "Coffee",

  "taskDescription": "I need a Coffee",

  "taskStatus": "PENDING"

}

Saved in the database as:

{

  "taskId": 1,                  // system generated

  "taskName": "Coffee",

  "taskDescription": "I need a Coffee",

  "taskStatus": "PENDING",

  "createdAt": "2026-02-18T12:00:00", // system generated

  "updatedAt": "2026-02-18T12:00:00"  // system generated

}

Update a Task

PUT http://localhost:8080/task/{taskId}

{

  "taskName": "Not Cycling101",

  "taskDescription": "Not Updated description101",

  "taskStatus": "PENDING"

}

Only the user who created the task can update it.

Delete a Task

DELETE http://localhost:8080/task/{taskId}

Only the user who created the task can delete it.

⸻

🔑 Notes

	•	Tasks are user-specific: no one can update or delete tasks created by others.
	•	All sensitive operations are secured using JWT authentication.
	•	taskId, createdAt, and updatedAt are automatically generated by the system.
	