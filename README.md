src/main/java/com/example/reactiveapp ├── config/ // Configuration files ├── handler/ // Request handlers (business logic) ├── model/ // Domain models ├── repository/ // Reactive repositories ├── router/ // Route configurations ├── service/ // Service classes (optional) ├── controller/ // Controllers (optional) └── ReactiveApplication.java // Main class

yaml
Copy
Edit

---

### 1. `model/`
- **Purpose**: Represents the domain/data model of the application.
- **Files**: POJOs annotated with Lombok or persistence annotations like `@Document`.
  
**Example:**
```java
public class User {
    private String id;
    private String name;
    private String email;
    // Getters and Setters
}
2. repository/
Purpose: Manages database interactions using a reactive approach.
Files: Interfaces extending ReactiveCrudRepository.
Example:

java
Copy
Edit
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByEmail(String email);
}
3. handler/
Purpose: Handles incoming requests and contains business logic.
Files: Classes annotated with @Component, returning Mono or Flux.
Example:

java
Copy
Edit
@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String userId = request.pathVariable("id");
        return userRepository.findById(userId)
            .flatMap(user -> ServerResponse.ok().bodyValue(user))
            .switchIfEmpty(ServerResponse.notFound().build());
    }
}
4. router/
Purpose: Defines routes and maps them to the corresponding handlers.
Files: Classes using RouterFunction for declarative routing.
Example:

java
Copy
Edit
@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return RouterFunctions.route()
            .GET("/users/{id}", userHandler::getUserById)
            .POST("/users", userHandler::createUser)
            .build();
    }
}
5. service/ (optional)
Purpose: Contains business logic between the handler and repository layers for complex workflows.
Files: Classes annotated with @Service.
Example:

java
Copy
Edit
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }
}
6. config/
Purpose: Stores application-specific configurations like WebFlux, database, or security setups.
Files: Classes annotated with @Configuration.
Example:

java
Copy
Edit
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {
    // Add WebFlux-specific configurations
}
7. controller/ (optional in reactive apps)
Purpose: For hybrid applications combining reactive and traditional MVC approaches.
Files: Classes annotated with @RestController.
Key Features of Reactive Programming
Non-blocking: Utilizes Mono and Flux for asynchronous, non-blocking streams.
Backpressure: Efficiently handles data flow with demand-based processing.
Declarative Routing: Uses functional-style routes (RouterFunction).
Quick Start
Create the folder structure above in your project.
Define your models, handlers, repositories, and routers as shown.
Run your ReactiveApplication.java:
java
Copy
Edit
@SpringBootApplication
public class ReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }
}
Feel free to extend this structure as your project grows! 🚀



## Reactive Micro Services Example
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](/LICENSE)
[![Build Status](https://travis-ci.org/LearningByExample/reactive-ms-example.svg?branch=master)](https://travis-ci.org/LearningByExample/reactive-ms-example)
[![codecov](https://codecov.io/gh/LearningByExample/reactive-ms-example/branch/master/graph/badge.svg)](https://codecov.io/gh/LearningByExample/reactive-ms-example)
[![codebeat badge](https://codebeat.co/badges/9f473a67-ab5a-4205-82fe-976e9bbb01e6)](https://codebeat.co/projects/github-com-learningbyexample-reactive-ms-example-master)

## info
This is an example of doing reactive MicroServices using spring 5 functional web framework and spring boot 2.

There is a [Kotlin fork](https://github.com/LearningByExample/KotlinReactiveMS) of this service.

This service provide and API that will get the geo location and the sunrise and sunset times from an address.

```Gherkin
Scenario: Get Location
  Given I've an address
  When I call the location service
  Then I should get a geo location
  And I should get the sunrise and sunset times
```
To implement this example we consume a couple of REST APIs.

This example cover several topics: 

- Functional programing.
- Reactive types.
- Router Functions.
- Static Web-Content.
- Creation on Reactive Java Services/Components.
- Error handling in routes and services.
- Reactive Web Client to consume external REST Services.
- Organizing your project in manageable packaging.

Includes and in depth look to testing using JUnit5:
- Unit, Integration and System tests.
- Mocking, including reactive functions and JSON responses.
- BDD style assertions.
- Test tags with maven profiles.

## usage

To run this service:

```shell
$ mvnw spring-boot:run
```

## Sample requests

Get from address
```shell
$ curl -X GET "http://localhost:8080/api/location/Trafalgar%20Square%2C%20London%2C%20England" -H  "accept: application/json"
```

Post from JSON
```shell
$ curl -X POST "http://localhost:8080/api/location" -H  "accept: application/json" -H  "content-type: application/json" -d "{  \"address\": \"Trafalgar Square, London, England\"}"
```

Both will produce something like:
```json
{
  "geographicCoordinates": {
    "latitude": 51.508039,
    "longitude": -0.128069
  },
  "sunriseSunset": {
    "sunrise": "2017-05-21T03:59:08+00:00",
    "sunset": "2017-05-21T19:55:11+00:00"
  }
}
```
_All date and times are ISO 8601 UTC without summer time adjustment_
## API
[![View in the embedded Swagger UI](https://avatars0.githubusercontent.com/u/7658037?v=3&s=20) View in the embedded Swagger UI](http://localhost:8080/index.html)

[![Run in Postman](https://lh4.googleusercontent.com/Dfqo9J42K7-xRvHW3GVpTU7YCa_zpy3kEDSIlKjpd2RAvVlNfZe5pn8Swaa4TgCWNTuOJOAfwWY=s20) Run in Postman](https://app.getpostman.com/run-collection/498aea143dc572212f17)

## Project Structure

- [main/java](/src/main/java/org/learning/by/example/reactive/microservices)
    - [/application](/src/main/java/org/learning/by/example/reactive/microservices/application) : Main Spring boot application and context configuration.  
    - [/routers](/src/main/java/org/learning/by/example/reactive/microservices/routers) : Reactive routing functions.
    - [/handlers](/src/main/java/org/learning/by/example/reactive/microservices/handlers) : Handlers used by the routers.
    - [/services](/src/main/java/org/learning/by/example/reactive/microservices/services) : Services for the business logic needed by handlers.
    - [/exceptions](/src/main/java/org/learning/by/example/reactive/microservices/exceptions) : Businesses exceptions.
    - [/model](/src/main/java/org/learning/by/example/reactive/microservices/model) : POJOs.
- [test/java](/src/test/java/org/learning/by/example/reactive/microservices)
    - [/application](/src/test/java/org/learning/by/example/reactive/microservices/application) : Application system and unit tests.
    - [/routers](/src/test/java/org/learning/by/example/reactive/microservices/routers) : Integration tests for routes.
    - [/handlers](/src/test/java/org/learning/by/example/reactive/microservices/handlers) : Unit tests for handlers.
    - [/services](/src/test/java/org/learning/by/example/reactive/microservices/services) : Unit tests for services.
    - [/model](/src/test/java/org/learning/by/example/reactive/microservices/model) : POJOs used by the test.
    - [/test](/src/test/java/org/learning/by/example/reactive/microservices/test) : Helpers and base classes for testing.

## References

- https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework
- https://spring.io/blog/2017/02/23/spring-framework-5-0-m5-update
- http://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven
- https://github.com/junit-team/junit5-samples
- https://developers.google.com/maps/documentation/geocoding/intro
- https://sunrise-sunset.org/api
- https://en.wikipedia.org/wiki/ISO_8601
