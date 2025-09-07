# Spring Boot

Spring Boot is a powerful framework for building Java applications. It simplifies the development process by providing pre-configured settings and eliminating boilerplate code, enabling developers to focus on writing business logic.



<details>
<summary><b>✅ What is a Bean in Spring?</b></summary>

**Definition:**  
Bean is simply an object that is managed by the Spring container.  

Spring instantiates, configures, and manages the lifecycle of beans.  

Beans are typically your application classes like services, repositories, controllers, etc.

**Example:**  
```java
@Component
public class MyService {
    public void serve() {
        System.out.println("Service is running...");
    }
}

</details>


<details>
<summary><b>Generally Asked Questions</b></summary>
<details>
<summary><b>1) What is framework?</b></summary>
A framework is a ready-made structure that provides reusable code and tools to build applications easily.  
<b>Example:</b> Spring Boot is a framework for building Java applications.
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
<details>
<summary><b>OOPs</b></summary>

Object-Oriented Programming helps organize code into reusable objects.  
**Real-world Example:** A **Car** can be treated as an object with properties (color, model) and behaviors (drive, brake).

</details>

<details>
<summary><b>Spring CRUD</b></summary>

Spring Boot simplifies Create, Read, Update, and Delete operations with minimal boilerplate code.  
**Real-world Example:** A **Library System** where you can add new books (Create), view books (Read), update book details (Update), or remove old books (Delete).

</details>

<details>
<summary><b>Basic Concepts</b></summary>

Spring Boot provides auto-configuration, starter dependencies, and an embedded server for faster development.  
**Real-world Example:** Like a **pre-cooked meal kit** where most ingredients are ready — you just assemble and start eating.

</details>

<details>
<summary><b>Kafka</b></summary>

Kafka is a distributed messaging system for real-time data streaming between applications.  
**Real-world Example:** Like a **news agency** that publishes headlines (producer), and many subscribers (consumers) get the updates instantly.

</details>

<details>
<summary><b>Spring Reactive</b></summary>

Spring Reactive (WebFlux) allows building non-blocking, high-performance applications using reactive streams.  
**Real-world Example:** Like a **restaurant with token system** where multiple customers are served efficiently without blocking others.

</details>
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

---

## Features

<details>
<summary>Click to view Features</summary>

- **OOPs**: Leverages object-oriented principles to organize and structure code effectively.  
- **Spring CRUD**: Provides a robust framework to perform Create, Read, Update, and Delete operations seamlessly.  
- **Basic Concepts**: Simplifies application setup with auto-configuration and starter dependencies.  
- **Kafka**: Offers reliable and scalable messaging for real-time data streaming.  
- **Spring Reactive**: Enables the development of highly performant, non-blocking applications using reactive programming.  

</details>

---

## Technology

<details>
<summary>Click to view Technology</summary>

This project is built using:
- **Java**: The primary programming language.  
- **Spring Boot**: The core framework for application development.  
- **MySQL**: The database used for this project.  
- **Kafka**: For real-time messaging and event-driven architecture.  

</details>

---

## Installation

<details>
<summary>Click to view Installation Guide</summary>

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



# 🧑‍💻 OOP Concepts in Java

## 1. Class & Object

**Class:** A blueprint (like a design of a car)  
**Object:** A real entity created from the class (like an actual car)

class Car {
String color;
void drive() {
System.out.println("Car is driving...");
}
}

public class Main {
public static void main(String[] args) {
Car car1 = new Car(); // object
car1.color = "Red";
car1.drive();
}
}

text

---

## 2. Encapsulation

Wrapping data and methods together, keeping fields **private** with public getters/setters.

class BankAccount {
private double balance; // hidden from outside

text
public void deposit(double amount) {
    balance += amount;
}
public double getBalance() {
    return balance;
}
}

public class Main {
public static void main(String[] args) {
BankAccount acc = new BankAccount();
acc.deposit(500);
System.out.println(acc.getBalance()); // Encapsulated access
}
}

text

---

## 3. Inheritance

One class inherits another to reuse code.

class Animal {
void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
void bark() { System.out.println("Barking..."); }
}

public class Main {
public static void main(String[] args) {
Dog d = new Dog();
d.eat(); // inherited
d.bark(); // own method
}
}

text

---

## 4. Polymorphism

Many forms → Same method behaves differently.

### (a) Compile-time Polymorphism (Method Overloading)

class MathUtil {
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }
}

public class Main {
public static void main(String[] args) {
MathUtil m = new MathUtil();
System.out.println(m.add(2, 3)); // calls int version
System.out.println(m.add(2.5, 3.5)); // calls double version
}
}

text

### (b) Runtime Polymorphism (Method Overriding)

class Animal {
void sound() { System.out.println("Animal makes sound"); }
}

class Dog extends Animal {
@Override
void sound() { System.out.println("Dog barks"); }
}

public class Main {
public static void main(String[] args) {
Animal a = new Dog(); // upcasting
a.sound(); // Runtime: calls Dog’s method
}
}

text

---

## 5. Abstraction

Hiding implementation details and showing only essential features.

abstract class Shape {
abstract void draw(); // no body
}

class Circle extends Shape {
void draw() { System.out.println("Drawing Circle"); }
}

public class Main {
public static void main(String[] args) {
Shape s = new Circle(); // abstraction
s.draw();
}
}

text

---

## 6. Interface

Defines a contract (all methods must be implemented).  
Supports multiple inheritance.

interface Vehicle {
void start();
}

class Bike implements Vehicle {
public void start() { System.out.println("Bike starts with kick"); }
}

public class Main {
public static void main(String[] args) {
Vehicle v = new Bike();
v.start();
}
}

text

---

## 7. Association / Aggregation / Composition

- **Association:** Relationship between two classes.
- **Aggregation:** "Has-a" relationship (independent).
- **Composition:** "Has-a" but dependent (strong ownership).

class Engine {
void start() { System.out.println("Engine starts"); }
}

class Car {
private Engine engine; // Composition
Car() { engine = new Engine(); }
void drive() { engine.start(); }
}

public class Main {
public static void main(String[] args) {
Car car = new Car();
car.drive();
}
}

text
undefined
