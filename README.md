# Expense Tracking API

This project is an Expense Tracking API built with Java and Spring Boot. It provides endpoints to manage expenses, using Spring Data JPA for MySQL database interactions.

## Key Features

- RESTful API for expense management
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

- `GET /expenses` - Retrieve a list of all expenses

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