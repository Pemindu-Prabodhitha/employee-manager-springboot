# Employee Manager

A Spring Boot application for managing employees (CRUD) using MySQL, Thymeleaf, and Lombok.

## Prerequisites
- Java 17 or 21
- MySQL (XAMPP or standalone)
- Maven

## How to Run
1. Start MySQL and ensure port 3306 is available.
2. Create a database named `employee_db` (or let the app create it automatically).
3. Copy `secrets.properties.example` to `secrets.properties` and update it with your MySQL password.
4. Run the application: `./mvnw spring-boot:run`
5. Open your browser and go to: `http://localhost:8081/employees`