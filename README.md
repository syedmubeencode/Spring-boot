
![image](https://github.com/user-attachments/assets/c18dcfa4-1d1c-4381-911d-5da060aa2b3b)



# Apache Kafka Setup Guide

## ✅ Run the Application

To send a message using the Kafka REST producer endpoint:

http://localhost:8080/rest/api/producer?message="syed"


---

## 🧭 Step-by-Step Instructions

### 1️⃣ Start Zookeeper

Open a Command Prompt and run:

cmd
cd D:\kafka\bin\windows
zookeeper-server-start.bat D:\kafka\config\zookeeper.properties

## 2️⃣ Start Kafka Server
Open a new Command Prompt and run:

cd D:\kafka\bin\windows
kafka-server-start.bat D:\kafka\config\server.properties


## 3️⃣ Create a Kafka Topic
Run the following command in Command Prompt:

cd D:\kafka\bin\windows
kafka-topics.bat --create --topic test-topic1 --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
📝 Note: test-topic1 is the topic name.

## 4️⃣ Consume Messages from the Topic
Run the following command:

cmd
Copy
Edit
cd D:\kafka\bin\windows
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic CodeDecodeTopic

## 📌 Notes
Ensure Kafka and Zookeeper are running before producing or consuming messages.

Update paths if Kafka is installed in a different directory.


Let me know if you’d like this saved to a file or want a GitHub-flavored badge/header style.
