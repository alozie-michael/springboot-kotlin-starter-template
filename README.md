# Spring Boot + Kotlin Starter Project Template

## Getting Started
This base project was created to help developers who wish to use springboot web and kotlin in their projects.

All the dependencies are already configured and ready to use. The project already contains a basic structure for the 
development of the API, it also contains sample code which shows you how to use the dependencies.

The project also contains a basic structure for the development of unit and integration tests.

## Pre-requisites
To use this project template you need to have the following tools installed:
- Java 17
- Gradle 7.2 or higher
- Docker

## Dependencies
- `Spring Boot 3.1.4`: Framework for creating web applications. Read more about it [here](https://spring.io/projects/spring-boot)
- `Kotlin 8.4`: Programming language
- `Spring Data JPA`: Helps to manage the database entities and repositories. Read more about it [here](https://spring.io/projects/spring-data-jpa)
- `Flyway Migration`: Helps to manage database migrations. Read more about it [here](https://flywaydb.org/)
- `Postgres`: Database (Chosen for this project)
- `Testcontainers`: Helps to run integration tests with a docker container. Read more about it [here](https://www.testcontainers.org/)
- `OpenFeign`: Helps to make REST calls
- `OpenAPI Generator`: Helps to generate the API client interface and DTOs from a OpenAPI specification file. 
  The generated code is located in the `generated` folder. Read more about it [here](https://openapi-generator.tech/docs/generators/kotlin-spring)
- `OpenAPI UI`: Swagger UI. Helps to visualize the API documentation. Read more about it [here](https://swagger.io/tools/swagger-ui/)
- `Lombok`: Helps to reduce boilerplate code
- `Jarkata Validation`: Bean validation API. Helps to validate DTOs
- `Mockk`: Mocking library for Kotlin. Helps to mock classes and objects. Read more about it [here](https://mockk.io/)
- `Mapstruct`: Mapping library. Helps to map DTOs to Entities and vice-versa. Read more about it [here](https://mapstruct.org/)

## Development
### GitHub Actions
This project contains a GitHub Actions workflow that runs the unit and integration tests on every push to the main branch.

You can customize the workflow to run your own tests or build on it to deploy your application.

## How to use
Simply fork this repository, make any necessary customization and start coding your API.

## Contributing
If you want to contribute to this project, please read the [CONTRIBUTING.md](CONTRIBUTING.md) file.
