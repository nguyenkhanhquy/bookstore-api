# Book Store API

Final term project of the "Mobile Programming" course - Book Store API.

## Table of Contents

1. [Class Diagram](#class-diagram)
1. [Prerequisites](#prerequisites)
1. [Installation](#installation)
1. [Project Structure](#project-structure)
1. [Technology Stack](#technology-stack)
1. [Usage](#usage)
1. [Author](#author)
1. [License](#license)
1. [References](#references)

## Class Diagram

![Class Diagram](./assets/class_diagram.jpg)

## Prerequisites

- Java Development Kit (JDK) 21 or later
- Maven: 3.9.6
- Spring Boot: 3.2.5

## Installation

1. Clone the project from the repository:

    ```sh
    git clone https://github.com/nguyenkhanhquy/bookstore-api.git
    ```

2. Adjust configurations if necessary.

3. Run the application:

    ```sh
    mvn spring-boot:run
    ```

## Project Structure

Here's a brief overview of the project's structure:

```sh
project-name/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── example/
│ │ │ └── projectname/
│ │ │ ├── controller/ # REST API controllers
│ │ │ ├── model/ # Domain models
│ │ │ ├── repository/ # Data access repositories
│ │ │ ├── service/ # Business logic services
│ │ │ └── ProjectNameApplication.java # Main Spring Boot application
│ │ ├── resources/
│ │ ├── static/ # Static resources (CSS, JavaScript, images)
│ │ ├── templates/ # Templates (Thymeleaf, Freemarker, etc.)
│ │ ├── application.properties # Spring Boot configuration
│ │ └── application.yml # Alternate configuration file (YAML format)
│ ├── test/
│ └── java/
│ └── com/
│ └── example/
│ └── projectname/
│ ├── controller/ # Test classes for controllers
│ ├── model/ # Test classes for models
│ ├── repository/ # Test classes for repositories
│ └── service/ # Test classes for services
├── mvnw # Maven Wrapper script (Unix)
├── mvnw.cmd # Maven Wrapper script (Windows)
├── pom.xml # Project Object Model file for Maven
└── README.md # Project README file
```

## Technology Stack

- Programming Language: Java.
- Frameworks/Libraries: Spring Framework, Spring Boot, Spring Data JPA, Hibernate.
- Database: MySQL.
- IDE: IntelliJ IDEA.
- Deployment: Heroku.
- Cloud Storage: AWS S3.

## Usage

Instructions on how to use the API and interact with its endpoints.

### Resources

- [`https://api.21110282.codes/swagger-ui.html`](https://api.21110282.codes/swagger-ui.html) : API documentation and testing.

### Endpoints

- `GET /api/v1/products` : Get a list of products.
- `GET /api/v1/products/{productId}` : Get product information by ID.
- `POST /api/v1/products` : Create a new product.
- `PUT /api/v1/products/{productId}` : Update product information.
- `DELETE /api/v1/products/{productId}` : Delete a product.
- . . .

## Author

- Author Name: `Nguyễn Khánh Quy`
- Email: <nguyenkhanhquy123@gmail.com>

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/nguyenkhanhquy/bookstore-api/blob/main/LICENSE) file for details.

## References

- [Project Book APP](https://github.com/nguyenkhanhquy/bookstore-app)
