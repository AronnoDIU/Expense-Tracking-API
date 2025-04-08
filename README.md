# Expense Tracking API

This project is a comprehensive Expense Tracking API developed using Java and the Spring Boot framework. It is designed to facilitate the management of both expenses and user accounts through a set of well-defined RESTful API endpoints. The application leverages Spring Data JPA for seamless interaction with a MySQL database, ensuring efficient data storage and retrieval. By incorporating robust features such as authentication, authorization, and encrypted password storage, the API provides a secure and scalable solution for expense tracking and user management.

## Key Features

- RESTful API for expense and user management
- MySQL database integration
- Spring Boot for rapid development
- Lombok for reducing boilerplate code
- Maven for project management and build automation
- Spring Security for authentication and authorization
- Encrypted password storage using `BCryptPasswordEncoder`

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
- `GET /api/v1/users` - Retrieve a list of all users
- `GET /api/v1/users/{id}` - Retrieve a user by ID
- `POST /api/v1/users` - Create a new user (passwords are encrypted before storage)
- `PUT /api/v1/users/{id}` - Update a user by ID (passwords are encrypted before storage)
- `DELETE /api/v1/users/{id}` - Delete a user by ID

### Expense Endpoints
- `GET /api/v1/expenses` - Retrieve a list of all expenses
- `GET /api/v1/expenses/{id}` - Retrieve an expense by ID
- `GET /api/v1/expenses/category` - Retrieve expenses by category
- `GET /api/v1/expenses/date` - Retrieve expenses by date range
- `POST /api/v1/expenses` - Create a new expense
- `PUT /api/v1/expenses/{id}` - Update an expense by ID
- `DELETE /api/v1/expenses/{id}` - Delete an expense by ID

## Security Features

- **Password Encryption**: All user passwords are encrypted using `BCryptPasswordEncoder` before being stored in the database.
- **Authentication**: Spring Security is used to authenticate users during login.
- **Authorization**: Endpoints are secured, and only authenticated users can access protected resources.

## Circular Dependency Resolution

A circular dependency issue was resolved between `SecurityConfig` and `UserServiceImpl` by directly injecting `BCryptPasswordEncoder` into `UserServiceImpl` and avoiding dependency on `SecurityConfig`. This ensures a clean and maintainable architecture.

## Project Structure

- `src/main/java/com/aronno/expensetracking_api` - Main source code
- `src/main/resources` - Configuration files
- `pom.xml` - Maven configuration file

## Dependencies

- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL Connector
- Lombok

## Troubleshooting

If you encounter `java.lang.ExceptionInInitializerError` with `com.sun.tools.javac.code.TypeTag :: UNKNOWN`, ensure that your project is configured to use a compatible Java version. Check the `pom.xml` file and make sure the `java.version` property is set correctly.

If you encounter a circular dependency error, ensure that the `spring.main.allow-circular-references` property is not set to `true` and verify the dependency injection setup in the `SecurityConfig` and `UserServiceImpl` classes.

## License

This project is licensed under the MIT License.