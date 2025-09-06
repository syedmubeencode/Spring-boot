# Spring Boot

Spring Boot is a powerful framework for building Java applications. It simplifies the development process by providing pre-configured settings and eliminating boilerplate code, enabling developers to focus on writing business logic.

This project is a Spring Boot application, created based on references from the Java Techie channel. It is a simple product service.

## Table of Contents
- OOPs
- Spring CRUD
- Basic Concepts
- Kafka
- Spring Reactive

## Introduction
Spring Boot is an extension of the Spring framework that provides a streamlined way to create standalone, production-ready applications. It integrates key concepts such as Object-Oriented Programming (OOPs), CRUD operations using Spring, and advanced technologies like Kafka for messaging and Spring Reactive for building asynchronous, non-blocking applications.

## Features
- **OOPs**: Leverages object-oriented principles to organize and structure code effectively.
- **Spring CRUD**: Provides a robust framework to perform Create, Read, Update, and Delete operations seamlessly.
- **Basic Concepts**: Simplifies application setup with auto-configuration and starter dependencies.
- **Kafka**: Offers reliable and scalable messaging for real-time data streaming.
- **Spring Reactive**: Enables the development of highly performant, non-blocking applications using reactive programming.

## Technology
This project is built using:
- **Java**: The primary programming language.
- **Spring Boot**: The core framework for application development.
- **MySQL**: The database used for this project.
- **Kafka**: For real-time messaging and event-driven architecture.

## Installation
To get started with Spring Boot, follow these steps:
1. Install Java Development Kit (JDK) version 8 or higher.
2. Download and set up your favorite Integrated Development Environment (IDE), such as IntelliJ IDEA or Eclipse.
3. Add Spring Boot dependencies to your `pom.xml` or `build.gradle` file.
4. Run your application using the Spring Boot starter class.
5. Import the Postman collection provided to test the APIs.

<details>
<summary>📂 Click to view detailed installation guide</summary>

- Ensure **MySQL** is installed and running.
- Create a database and update the connection details in `application.properties`.
- Use the command below to run the Spring Boot application:
  ```bash
  mvn spring-boot:run
