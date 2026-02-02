🔐 Secure Task Management Application

Spring Boot • Spring Security • PostgreSQL

📅 Learning Log – 02.02.2026

⸻

📌 Project Overview

This project is a Spring Boot application that implements database-based authentication using Spring Security and PostgreSQL.

On 02.02.2026, I successfully implemented and understood how Spring Security authenticates users stored in a database, how custom security components work together, and how password encoding affects authentication.

This README will be updated day by day to track what I learn and implement as the project evolves.

⸻

🛠️ Technologies Used
	•	Java
	•	Spring Boot
	•	Spring Security
	•	Spring Data JPA
	•	PostgreSQL
	•	Postman (for API testing)

⸻

🎯 What I Learned & Implemented Today (02.02.2026)
	•	Connected a Spring Boot application to a PostgreSQL database
	•	Implemented custom authentication using Spring Security
	•	Understood the difference between:
	•	PostgreSQL users (DB login)
	•	Spring Security users (application authentication)
	•	Implemented UserDetailsService to load users from the database
	•	Created a custom UserDetails implementation (UserPrincipal)
	•	Debugged 401 Unauthorized errors
	•	Learned why Spring Security requires password encoding
	•	Fixed authentication using {noop} for learning purposes

⸻

🗂️ Application Architecture

🔹 Database Layer (PostgreSQL)
	•	Database name: telusko1
	•	Table: users

Table structure:
	•	id (Primary Key)
	•	username
	•	password

For learning and debugging purposes, passwords are stored with a prefix:
{noop}s@123
Entity
@Entity
public class Users {
    @Id
    private int id;
    private String username;
    private String password;
}
	•	Represents the users table
	•	Used by JPA to map database records to Java objects
🔹 Repository Layer (UserRepo)
@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
	•	Communicates with the database
	•	Fetches user records by username
	•	Used during authentication by Spring Security
🔹 Custom UserDetails (UserPrincipal)
public class UserPrincipal implements UserDetails {
    private Users user;
}
Responsibilities:
	•	Wraps the Users entity
	•	Supplies Spring Security with:
	•	username
	•	password
	•	authorities
	•	account status flags

This acts as the bridge between database users and Spring Security.

🔹 UserDetailsService Implementation (MyUserDetailsService)
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;
}
Responsibilities:
	•	Loads user data from PostgreSQL using UserRepo
	•	Throws UsernameNotFoundException if user does not exist
	•	Returns a UserPrincipal object to Spring Security

This is the core of database-based authentication.
🔹 Spring Security Configuration
	•	CSRF disabled (for API testing)
	•	All endpoints require authentication
	•	Supports:
	•	Form Login (browser)
	•	HTTP Basic Authentication (Postman)
	•	Stateless session policy enabled

This ensures every request is authenticated properly.
🧪 Authentication Testing

Authentication was tested using Postman with Basic Auth:
	•	Username: sushil
	•	Password: s@123

Successful authentication confirmed:
	•	Database connection works
	•	User loading works
	•	Password comparison works
	•	Spring Security configuration is correct
