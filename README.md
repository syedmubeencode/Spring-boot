# Spring Boot

Spring Boot is a powerful framework for building Java applications. It simplifies the development process by providing pre-configured settings and eliminating boilerplate code, enabling developers to focus on writing business logic.
<details>
<summary><b>Generally Asked Questions</b></summary>
<details>
<summary><b>1) What is framework?</b></summary>
A framework is a ready-made structure that provides reusable code and tools to build applications easily.  
**Example:** Spring Boot is a framework for building Java applications.
</details>
<details>
<summary><b>2) What are pre-configured settings in Spring Boot, give example?</b></summary>
Pre-configured settings are default configurations provided by Spring Boot to save setup time.  
**Example:** Spring Boot automatically configures Tomcat as the default server when you add `spring-boot-starter-web`.
</details>
<details>
<summary><b>3) What is business logic, give example?</b></summary>
Business logic is the core set of rules or operations that solve real-world problems in an application.  
**Example:** In a banking app, calculating interest on savings is business logic.
</details>
</details>

## Table of Contents

<details>
<summary><b>Core Java & OOPs</b></summary>

- OOPs Concepts  
- Collections Framework  
- Exception Handling  
- Multithreading & Concurrency  
- Java 8 Features (Streams, Lambda, Optional)  

</details>

<details>
<summary><b>Spring Boot Basics</b></summary>

- Introduction to Spring & Spring Boot  
- Dependency Injection & IoC  
- Spring Boot Annotations  
- Spring Boot Starter Projects  
- Configuration (application.properties / application.yml)  
- Profiles & Environment Setup  

</details>

<details>
<summary><b>Spring Boot with Data</b></summary>

- Spring Data JPA  
- Hibernate Basics  
- CRUD Operations  
- Entity Relationships (OneToOne, OneToMany, ManyToMany)  
- Transaction Management  
- Pagination & Sorting  

</details>

<details>
<summary><b>RESTful Web Services</b></summary>

- Creating REST APIs  
- Request & Response Mapping  
- Exception Handling (Controller Advice)  
- Validation  
- HATEOAS  
- Swagger / OpenAPI Documentation  

</details>

<details>
<summary><b>Advanced Spring Boot</b></summary>

- Spring Security (Authentication & Authorization)  
- JWT (JSON Web Token) Integration  
- AOP (Aspect Oriented Programming)  
- Caching (EhCache, Redis)  
- Scheduling  
- Actuator & Health Checks  

</details>

<details>
<summary><b>Messaging & Streaming</b></summary>

- Kafka Integration  
- RabbitMQ  
- JMS  

</details>

<details>
<summary><b>Spring Reactive</b></summary>

- Introduction to Reactive Programming  
- WebFlux vs MVC  
- Mono & Flux  
- Reactive Repositories  

</details>

<details>
<summary><b>Testing</b></summary>

- Unit Testing with JUnit & Mockito  
- Integration Testing  
- TestContainers  
- MockMvc  

</details>

<details>
<summary><b>Microservices & Cloud</b></summary>

- Microservices Architecture  
- Service Discovery (Eureka, Consul)  
- API Gateway (Spring Cloud Gateway)  
- Circuit Breaker (Resilience4j / Hystrix)  
- Config Server & Distributed Config  
- Observability (Sleuth, Zipkin, Micrometer, Prometheus, Grafana)  
- Docker & Kubernetes Basics  

</details>

<details>
<summary><b>Extras (Good to Know)</b></summary>

- Design Patterns in Spring  
- Performance Tuning  
- Common Interview Scenarios & FAQs  

</details>

## Introduction
Spring Boot is an extension of the Spring framework that provides a streamlined way to create standalone, production-ready applications. It integrates key concepts such as Object-Oriented Programming (OOPs), CRUD operations using Spring, and advanced technologies like Kafka for messaging and Spring Reactive for building asynchronous, non-blocking applications.

<details>
<summary>📂 Click to view Introduction</summary>

More detailed explanation of **Introduction** here...

</details>

---

## Features

<details>
<summary>📂 Click to view Features</summary>

- **OOPs**: Leverages object-oriented principles to organize and structure code effectively.  
- **Spring CRUD**: Provides a robust framework to perform Create, Read, Update, and Delete operations seamlessly.  
- **Basic Concepts**: Simplifies application setup with auto-configuration and starter dependencies.  
- **Kafka**: Offers reliable and scalable messaging for real-time data streaming.  
- **Spring Reactive**: Enables the development of highly performant, non-blocking applications using reactive programming.  

</details>

---

## Technology

<details>
<summary>📂 Click to view Technology</summary>

This project is built using:
- **Java**: The primary programming language.  
- **Spring Boot**: The core framework for application development.  
- **MySQL**: The database used for this project.  
- **Kafka**: For real-time messaging and event-driven architecture.  

</details>

---

## Installation

<details>
<summary>📂 Click to view Installation Guide</summary>

1. Install Java Development Kit (JDK) version 8 or higher.  
2. Download and set up your favorite Integrated Development Environment (IDE), such as IntelliJ IDEA or Eclipse.  
3. Add Spring Boot dependencies to your `pom.xml` or `build.gradle` file.  
4. Run your application using the Spring Boot starter class.  
5. Import the Postman collection provided to test the APIs.  

**Additional Notes:**
- Ensure **MySQL** is installed and running.  
- Create a database and update the connection details in `application.properties`.  
- Use the command below to run the Spring Boot application:  
  ```bash
  mvn spring-boot:run
