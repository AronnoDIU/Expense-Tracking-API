# Expense Tracking API

🚀 A state-of-the-art expense management system built with Spring Boot and MySQL, offering a secure and scalable RESTful API for seamless personal finance tracking. This enterprise-grade solution features JWT authentication, real-time expense monitoring, and comprehensive user management, empowering users to take control of their financial journey with confidence.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## Features

- User Authentication with JWT
- Expense Management (CRUD operations)
- Category Management
- Rate Limiting (20 requests/minute per endpoint)
- Caffeine Caching with 60-minute expiration
- Response Compression for better performance
- Production-ready configuration
- API Documentation
- Monitoring with Spring Actuator

## Performance Optimizations

- **Caching Strategy**:
  - Caffeine cache with 500 max entries
  - 60-minute expiration time
  - Cache statistics monitoring
  - Smart cache key generation using authentication context

- **Rate Limiting**:
  - Token bucket algorithm using Bucket4j
  - 20 requests per minute per endpoint
  - Rate limit headers in responses

- **Response Optimization**:
  - Compression enabled for JSON/XML responses
  - 1KB minimum compression threshold
  - Optimized cache headers

## Tech Stack

- Java 17
- Spring Boot 3.2.3
- Spring Security with JWT
- MySQL 8
- Hibernate
- Maven
- Docker
- Caffeine Cache
- Bucket4j for Rate Limiting

## Prerequisites

- JDK 17
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

3. Configure environment variables:
```bash
export MYSQL_URL=jdbc:mysql://localhost:3306/expense_tracking
export MYSQL_USERNAME=your_username
export MYSQL_PASSWORD=your_password
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
- GET `/api/v1/user/profile` - Get user profile (cached)
- PUT `/api/v1/user/profile` - Update user profile
- DELETE `/api/v1/user/profile` - Delete user

### Expense Management
- GET `/api/v1/expenses` - Get all expenses (cached)
- GET `/api/v1/expenses/{id}` - Get expense by ID (cached)
- POST `/api/v1/expenses` - Create new expense
- PUT `/api/v1/expenses/{id}` - Update expense
- DELETE `/api/v1/expenses/{id}` - Delete expense

## Production Deployment

### Prerequisites
- Docker and Docker Compose installed
- JDK 17 for building
- Maven for building

### Build and Deploy

1. Build the application:
```bash
mvn clean package -DskipTests
```

2. Run with Docker Compose:
```bash
docker-compose up -d
```

## Performance Monitoring

The application exposes several endpoints for monitoring:

- `/actuator/health` - System health information
- `/actuator/metrics` - Metrics including cache statistics
- `/actuator/prometheus` - Prometheus metrics

## Cache Management

The application uses Caffeine cache with the following configuration:

```yaml
spring:
  cache:
    cache-names: users,expenses,categories
    caffeine:
      spec: maximumSize=500,expireAfterWrite=60m
```

## Rate Limiting

Rate limiting is configured per endpoint with the following defaults:
- 20 requests per minute
- Rate limit headers included in responses:
  - X-Rate-Limit-Remaining
  - X-Rate-Limit-Retry-After-Seconds

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.