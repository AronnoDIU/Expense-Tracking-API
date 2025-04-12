# Expense Tracking API

🚀 A state-of-the-art expense management system built with Spring Boot and MySQL, offering a secure and scalable RESTful API for seamless personal finance tracking. This enterprise-grade solution features JWT authentication, real-time expense monitoring, and comprehensive user management, empowering users to take control of their financial journey with confidence.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## Features

- User Authentication with JWT
- Expense Management (CRUD operations)
- Category Management
- Rate Limiting
- Caching for improved performance
- Production-ready configuration
- API Documentation
- Monitoring with Spring Actuator

## Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring Security with JWT
- MySQL 8
- Hibernate
- Maven
- Docker
- Caffeine Cache
- Bucket4j for Rate Limiting

## Prerequisites

- JDK 21
- Maven 3.x
- MySQL 8.x
- Docker (for production deployment)

## Local Development Setup

1. Clone the repository:
```bash
git clone https://github.com/AronnoDIU/Expense-Tracking-API.git
cd Expense-Tracking-API
```

2. Configure MySQL:
```sql
CREATE DATABASE expense_tracking;
```

3. Update application.properties with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracking
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Build and run:
```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/v1`

## API Endpoints

### Authentication
- POST `/api/v1/auth/register` - Register new user
- POST `/api/v1/auth/login` - Login user

### User Management
- GET `/api/v1/user/profile` - Get user profile
- PUT `/api/v1/user/profile` - Update user profile
- DELETE `/api/v1/user/profile/delete` - Delete user

### Expense Management
- GET `/api/v1/expenses` - Get all expenses
- GET `/api/v1/expenses/{id}` - Get expense by ID
- POST `/api/v1/expenses` - Create new expense
- PUT `/api/v1/expenses/{id}` - Update expense
- DELETE `/api/v1/expenses/{id}` - Delete expense

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

### Security Features
- JWT Authentication
- Rate Limiting (100 requests per minute per client)
- Secure Headers
- HTTPS in production
- Database connection pooling
- Password encryption

### Performance Features
- Response Caching
- Connection Pooling
- Optimized JVM settings
- Database indexing

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.