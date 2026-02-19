🔐 Secure Task Management Application

Spring Boot • Spring Security •JWT Token• PostgreSQL

⸻

📌 Project Status & Roadmap

✅ Accomplished So Far

	•	Implemented user registration and login with Spring Security and JWT-based authentication.

	•	Users can create, update, and delete tasks, with ownership validation (only task creators can modify their tasks).

	•	Tasks include automatic timestamps (createdAt, updatedAt) and system-generated taskId.

	•	Implemented database persistence using PostgreSQL.

	•	Ensured secure and user-specific access to all task operations.

🔜 Future Enhancements

	1.	Role-Based Users: Only admins can delete or modify other users.

	2.	Swagger Integration: Add API documentation for easier testing and visualization.

	3.	Liquibase: Implement database versioning and migration management.

⸻

📌 Project Overview

This is a Task Management System built with Spring Boot (version 3.5.10).

The application allows users to create, update, and delete tasks securely. A user can only update or delete tasks they have created.

This project also implements database-based authentication using Spring Security and PostgreSQL with JWT tokens for secure access.

This README will be updated regularly to track my learning and development progress.

⸻

🛠 Features
	•	User authentication using Spring Security and JWT.
	•	Create a new task.
	•	Update or delete tasks only if the user created them.
	•	Automatic timestamps (createdAt, updatedAt) and system-generated taskId.

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

🔐 Authentication Flow (JWT)
User                  Backend API
  |                        |
  | --- Register ---------->|
  |                        |
  |<-- Success Response ----|
  |                        |
  | --- Login ------------->|
  |                        |
  |<-- JWT Token -----------|  (Expires in X hours)
  |                        |
  | --- API Request --------|  (Attach JWT as Bearer Token)
  |                        |
  |<-- Authorized Response -|

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

    "password": "u@123"

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

📊 Task CRUD Flow Diagram (Concept)

+---------+       +------------------+       +------------+
|  User   | --->  | TaskController   | --->  | TaskService |
+---------+       +------------------+       +------------+
     |                    |                        |
     | Create Task        |                        |
     |------------------->|                        |
     |                    | validate user & task   |
     |                    |---------------------->|
     |                    |                        |
     |                    |      Save/Update/Delete|
     |                    |<----------------------|
     |<-------------------|                        |
     |  Response with Task|                        |

	 User sends request → Controller → Service → Repository → Database → Response.

	 All operations are validated for ownership and secured with JWT.

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
	