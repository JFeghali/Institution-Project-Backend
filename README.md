# My Spring Boot Project

A simple Spring Boot application that demonstrates CRUD operations with a RESTful API.
The project contains a JWT authentication implementation with unit tests for the institution service.



## Usage
Once the application is running, you can access the following endpoints:

POST api/v1/register: to register a new user(please you should call his api using postman or swagger-ui because there is no sign-up page in the frontend only a login page was implemented)
POST api/v1/authenticate: to login 

All the others endpoints description are available in the postman collection

### Installation

Clone the repository, navigate to the project directory, build the project using Maven, and run the application:

cd institution-project
```bash

mvn clean install
mvn spring-boot:run


