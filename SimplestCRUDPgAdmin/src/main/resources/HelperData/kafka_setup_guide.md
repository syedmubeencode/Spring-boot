# Kafka Local Setup and Spring Boot Testing Guide

This guide outlines the steps to start a local Kafka environment on Windows and test it using a Spring Boot application.

## 1. Start Kafka Environment

Follow these steps in order using separate terminal windows.

### Terminal 1: Start Zookeeper
```dos
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

### Terminal 2: Start Kafka Broker
Wait for the "Kafka Server started" message after running this command.
```dos
bin\windows\kafka-server-start.bat config\server.properties
```

### Terminal 3: Create Topic
```dos
bin\windows\kafka-topics.bat --create --topic my_topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

---

## 2. Run and Test Spring Boot Application

1. **Launch the Application**: Run the Spring Boot app via your IDE (IntelliJ/Eclipse) or using the Maven command:
   ```bash
   mvn spring-boot:run
   ```

2. **Publish a Message**: Open your browser and navigate to the following URL:
   `http://localhost:8080/api/kafka/publish?msg=Testing_Syed_Mubeen_Kafka`

3. **Verify Result**: Check your **IDE Console**. The message printed by the `@KafkaListener` should appear almost instantly.

---

## 3. Troubleshooting: Port Management

If you encounter "Address already in use" errors, you must kill the processes currently occupying the ports.

### Kill Active Kafka Processes (Port 9092)
Run these commands to clear port 9092:
```dos
taskkill /F /PID 14108
taskkill /F /PID 2840
```

### Check Zookeeper Status (Port 2181)
Kafka will fail to start if Zookeeper is stuck. Verify if port 2181 is occupied:
```dos
netstat -ano | findstr :2181
```