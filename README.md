# Expense Tracking API

🚀 A state-of-the-art expense management system built with Spring Boot and MySQL, offering a secure and scalable RESTful API for seamless personal finance tracking. This enterprise-grade solution features JWT authentication, real-time expense monitoring, and comprehensive user management, empowering users to take control of their financial journey with confidence.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## Features

- User Authentication with JWT
- User Profile Management
- Expense Management (CRUD operations)
- Secure API Endpoints
- Input Validation
- Error Handling
- MySQL Database Integration

## Technologies

- Java 21
- Spring Boot 3.x
- Spring Security with JWT
- MySQL 8
- Maven
- Lombok
- Hibernate/JPA

## Prerequisites

- Java 21 or higher
- MySQL 8.0 or higher
- Maven 3.x

## Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AronnoDIU/Expense-Tracking-API.git
   cd Expense-Tracking-API
   ```

2. **Configure MySQL**
   - Create a database named `expense_tracking`
   - Update `src/main/resources/application.properties` with your MySQL credentials if different

3. **Update JWT Secret**
   - In `application.properties`, update the JWT secret key:
     ```properties
     jwt.secret=your_secure_secret_key_here
     ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080/api/v1`

## API Documentation

### Authentication
- `POST /api/v1/auth/register` - Register a new user
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "dateOfBirth": "1990-01-01",
    "role": "USER",
    "phoneNumber": "1234567890",
    "address": "123 Main St"
  }
  ```

- `POST /api/v1/auth/login` - Login and get JWT token
  ```json
  {
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
  Response:
  ```json
  {
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9..."
  }
  ```

### User Profile
- `GET /api/v1/user/profile` - Get user profile
- `PUT /api/v1/user/profile` - Update user profile
- `DELETE /api/v1/user/profile` - Delete user account

### Expenses
- `GET /api/v1/expenses` - Get all expenses
- `GET /api/v1/expenses/{id}` - Get expense by ID
- `POST /api/v1/expenses` - Create new expense
  ```json
  {
    "name": "Grocery Shopping",
    "description": "Monthly groceries",
    "amount": 150.50,
    "category": "GROCERIES",
    "date": "2025-04-11"
  }
  ```
- `PUT /api/v1/expenses/{id}` - Update expense
- `DELETE /api/v1/expenses/{id}` - Delete expense

## Security

- JWT-based authentication
- Password encryption using BCrypt
- Role-based authorization
- Secure endpoints with Spring Security
- Input validation and sanitization
- Stateless session management

## Error Handling

The API uses a standardized error response format:
```json
{
  "status": 400,
  "message": "Error message here",
  "timestamp": "2025-04-11T18:26:20+06:00"
}
```

## Database Schema

### Users Table
- id (Primary Key)
- first_name
- last_name
- email (Unique)
- password (BCrypt encoded)
- date_of_birth
- role
- phone_number
- address
- created_at
- updated_at

### Expenses Table
- id (Primary Key)
- expense_name
- description
- expense_amount
- category
- expense_date
- user_id (Foreign Key)
- created_at
- updated_at

## Production Deployment

### Prerequisites
- Docker and Docker Compose installed
- JDK 21 for building
- Maven for building

### Build and Deploy

1. Build the application:
```bash
mvn clean package -DskipTests
```

2. Configure environment variables:
Edit the `docker-compose.yml` file and update the following environment variables:
- PROD_DB_USERNAME
- PROD_DB_PASSWORD
- JWT_SECRET
- MYSQL_ROOT_PASSWORD

3. Start the application:
```bash
docker-compose up -d
```

The application will be available at `http://your-domain:8080/api/v1`

### Monitoring
- Health check endpoint: `/api/v1/actuator/health`
- Metrics endpoint: `/api/v1/actuator/metrics`

### Logs
Logs are stored in `/var/log/expense-tracking/application.log`

### Security Notes
- Always change default passwords in production
- Use HTTPS in production
- Regularly update dependencies
- Monitor application metrics and logs
- Set up proper backup for the database

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

[YEASIR ARAFAT ARONNO](https://github.com/AronnoDIU)

## Acknowledgments

- Spring Boot Team
- MySQL Team
- All contributors