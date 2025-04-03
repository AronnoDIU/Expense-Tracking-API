# Expense Tracking API

This project is an Expense Tracking API built with Java and Spring Boot. It provides endpoints to manage expenses and users, using Spring Data JPA for MySQL database interactions.

## Key Features

- RESTful API for expense and user management
- MySQL database integration
- Spring Boot for rapid development
- Lombok for reducing boilerplate code
- Maven for project management and build automation

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

### User Endpoints
- `GET /api/v1/users` - Retrieve a list of all users
- `GET /api/v1/users/{id}` - Retrieve a user by ID
- `POST /api/v1/users` - Create a new user
- `PUT /api/v1/users/{id}` - Update a user by ID
- `DELETE /api/v1/users/{id}` - Delete a user by ID

### Expense Endpoints
- `GET /api/v1/expenses` - Retrieve a list of all expenses
- `GET /api/v1/expenses/{id}` - Retrieve an expense by ID
- `GET /api/v1/expenses/category` - Retrieve expenses by category
- `GET /api/v1/expenses/date` - Retrieve expenses by date range
- `POST /api/v1/expenses` - Create a new expense
- `PUT /api/v1/expenses/{id}` - Update an expense by ID
- `DELETE /api/v1/expenses/{id}` - Delete an expense by ID

## Project Structure

- `src/main/java/com/aronno/expensetracking_api` - Main source code
- `src/main/resources` - Configuration files
- `pom.xml` - Maven configuration file

## Dependencies

- Spring Boot
- Spring Data JPA
- MySQL Connector
- Lombok

## Troubleshooting

If you encounter `java.lang.ExceptionInInitializerError` with `com.sun.tools.javac.code.TypeTag :: UNKNOWN`, ensure that your project is configured to use a compatible Java version. Check the `pom.xml` file and make sure the `java.version` property is set correctly.

## License

This project is licensed under the MIT License.