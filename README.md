# 🏦 Banking System POC - Microservices with Spring Boot, Kafka & PostgreSQL

This project is a simplified **banking system** built with **Spring Boot microservices**, showcasing:
- REST APIs for account & transaction management
- Event-driven communication with **Apache Kafka**
- Persistence using **PostgreSQL**
- Clean, modular microservices architecture

---

## 📂 Services Overview

### 1️⃣ Account Service (`account-service`)
- Manages bank accounts (create, fetch, update).
- Exposes REST APIs to store and retrieve account information.
- Tech stack: **Spring Boot, Spring Data JPA, PostgreSQL**

Endpoints:
- `POST /accounts` → Create account
- `GET /accounts/{id}` → Get account by ID
- `GET /accounts` → List all accounts

### 2️⃣ Transaction Service (`transaction-service`)
- Handles deposits and withdrawals for accounts.
- Publishes **Kafka events** whenever a transaction happens.
- Stores all transactions in PostgreSQL.

Endpoints:
- `POST /transactions/deposit` → Deposit money into an account
- `POST /transactions/withdraw` → Withdraw money from an account
- `GET /transactions` → Get all transactions

### 3️⃣ Notification Service (`notification-service`)
- Listens to **Kafka topic: `transactions`**
- Acts as a consumer that prints/logs transaction events
- Demonstrates **asynchronous, decoupled microservice communication**

## ▶️ How to Run

### 1. Start Dependencies
- PostgreSQL (local or docker)
- Kafka & Zookeeper (local or docker)
### 2. Start Project
- PostgreSQL (local or docker)
- Kafka & Zookeeper (local or docker)
- mvn spring-boot:run (All services)
### 3. Start Kafka
- PostgreSQL (local or docker)
```bash
# Start Zookeeper
bin\windows\zookeeper-server-start.bat config\zookeeper.properties

# Start Kafka
bin\windows\kafka-server-start.bat config\server.properties

#Check Topic 
bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list

#Create Topic Manually
bin\windows\kafka-topics.bat --create --topic transactions --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Check Topic Logs
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic transactions --from-beginning
```
### 4. Test using postman Collection
- Load the Banking collection 
- Notification Service should log the event.
---

## Structure to Refer.
```
banking-system/
│
├── account-service/                 # 🏦 Manage bank accounts
│   ├── src/main/java/com/example/account
│   │   ├── model/Account.java        # Entity
│   │   ├── controller/AccountController.java
│   │   └── repository/AccountRepository.java
│   └── resources/application.yml
│
├── transaction-service/             # 💰 Handle deposits & withdrawals
│   ├── src/main/java/com/example/transaction
│   │   ├── model/Transaction.java    # Entity
│   │   ├── controller/TransactionController.java
│   │   ├── repository/TransactionRepository.java
│   │   └── config/KafkaProducerConfig.java
│   └── resources/application.yml
│
├── notification-service/             # 📩 Listens to Kafka events
│   ├── src/main/java/com/example/notification
│   │   └── service/TransactionConsumer.java
│   └── resources/application.yml
│
├── docker-compose.yml (optional)     # For PostgreSQL & Kafka
└── README.md                         # Project documentation
```
---
