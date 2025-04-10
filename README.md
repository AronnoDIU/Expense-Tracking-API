# Expense Tracking API

This project is a comprehensive Expense Tracking API developed using Java and the Spring Boot framework. It is designed to facilitate the management of both expenses and user accounts through a set of well-defined RESTful API endpoints. The application leverages Spring Data JPA for seamless interaction with a MySQL database, ensuring efficient data storage and retrieval. By incorporating robust features such as authentication, authorization, and encrypted password storage, the API provides a secure and scalable solution for expense tracking and user management.

## Key Features

- RESTful API for expense and user management
- MySQL database integration
- Spring Boot for rapid development
- Lombok for reducing boilerplate code
- Maven for project management and build automation
- Spring Security with custom UserDetailsService for authentication
- Encrypted password storage using `BCryptPasswordEncoder`
- Exception handling with detailed error responses
- Pagination support for expense listing

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/AronnoDIU/Expense-Tracking_API.git
    cd Expense-Tracking_API
    ```

2. Configure the database:
   - Update the MySQL database configuration in `src/main/resources/application.properties`:
       ```ini
       spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracking
       spring.datasource.username=root
       spring.datasource.password=root
       ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication Endpoints
- `POST /api/v1/auth/register` - Register a new user (passwords are encrypted before storage)
- `POST /api/v1/auth/login` - Login a user (passwords are validated using `BCryptPasswordEncoder`)

### User Endpoints
- `GET /api/v1/user/profile` - Retrieve the currently logged-in user's profile.
- `POST /api/v1/user/profile` - Create a new user.
- `PUT /api/v1/user/profile` - Update the currently logged-in user's profile.
- `DELETE /api/v1/user/profile/delete` - Delete the currently logged-in user's profile.

### Expense Endpoints
- `GET /expenses` - Retrieve a list of all expenses (paginated)
- `GET /expenses/{id}` - Retrieve an expense by ID
- `GET /expenses/category?category={category}` - Retrieve expenses by category
- `GET /expenses/date?startDate={startDate}&endDate={endDate}` - Retrieve expenses within a date range
- `POST /expenses` - Create a new expense
  ```json
  {
    "description": "Grocery shopping",
    "amount": 150.50,
    "category": "GROCERIES",
    "date": "2025-04-10"
  }
  ```
- `PUT /expenses/{id}` - Update an expense by ID
- `DELETE /expenses/{id}` - Delete an expense by ID

## Security Implementation

### Authentication Flow
1. User registration with encrypted password storage
2. Custom UserDetailsService for loading user data
3. DaoAuthenticationProvider for authentication
4. JWT-based session management (coming soon)

### Security Features
- Password encryption using `BCryptPasswordEncoder`
- Custom UserDetailsService implementation
- Proper exception handling for authentication failures
- Protected endpoints requiring authentication
- CSRF protection disabled for API endpoints

## Error Handling

The API provides detailed error responses in the following format:
```json
{
    "statusCode": 400,
    "message": "Error description",
    "timestamp": "2025-04-10T15:30:00"
}
```

Common error scenarios:
- 400 Bad Request - Invalid input data
- 401 Unauthorized - Invalid credentials
- 403 Forbidden - Insufficient permissions
- 404 Not Found - Resource not found
- 500 Internal Server Error - Server-side issues

## Project Structure

```
src/main/java/com/aronno/expensetracking_api/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── UserController.java
│   └── ExpenseController.java
├── entity/
│   ├── User.java
│   └── Expense.java
├── repository/
│   ├── UserRepository.java
│   └── ExpenseRepository.java
├── service/
│   ├── CustomUserDetailsService.java
│   ├── UserService.java
│   └── ExpenseService.java
└── ExpenseTrackingApiApplication.java
```

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- MySQL Connector Java
- Lombok
- Spring Boot Starter Validation

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.