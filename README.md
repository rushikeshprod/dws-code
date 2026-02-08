Trade Store Application — README
📌 1️⃣ Project Overview

The Trade Store Application is an enterprise-grade trade lifecycle processing system built using Spring Boot.

It simulates high-volume trade ingestion, validates business rules, stores trades in structured and unstructured databases, and publishes trade events to a streaming platform for downstream systems.

The platform demonstrates modern investment-banking architecture patterns including:

Event-driven design

Dual persistence

Trade validation engine

Expiry lifecycle management

Streaming analytics enablement

This solution aligns with enterprise data platform expectations such as those at large asset management organizations.

🧩 2️⃣ Problem Statement Coverage
Trade Attributes
Field	Description
Trade Id	Unique trade identifier
Version	Trade version number
Counter Party Id	Trade counterparty
Book Id	Trading book
Maturity Date	Trade maturity
Created Date	Trade creation date
Expired	Expiry flag
✅ Implemented Validations

Rule 1 — Lower Version Rejection
If an incoming trade has a lower version than an existing trade → rejected.

Rule 2 — Same Version Override
If version matches → existing trade replaced.

Rule 3 — Maturity Date Validation
Trades with maturity date earlier than today → rejected.

Rule 4 — Expiry Automation
Trades past maturity → automatically marked expired.

🏗️ 3️⃣ Architecture Overview
+------------------+
|   REST Client    |
+--------+---------+
         |
         ▼
+------------------+
| Trade Controller |
+--------+---------+
         |
         ▼
+------------------+
| Trade Service    |
| Validation Layer |
+---+----+----+----+
    |    |    |
    ▼    ▼    ▼
   SQL Mongo Kafka
 (State)(Audit)(Stream)

📐 4️⃣ UML Diagrams (PlantUML)

Render using: https://plantuml.com/plantuml

4.1 Sequence Diagram
@startuml

actor Client

Client -> TradeController : POST /trades
TradeController -> TradeService : processTrade()

TradeService -> ValidationService : validateTrade()
ValidationService --> TradeService : Valid / Exception

TradeService -> TradeRepository : save()
TradeRepository --> TradeService : Trade saved

TradeService -> TradeEventRepository : save event
TradeService -> KafkaProducer : publish trade

KafkaProducer --> KafkaTopic : trade-topic

TradeService --> TradeController : Response

@enduml

4.2 Class Diagram
@startuml

class Trade {
  tradeId
  version
  createdDate
  maturityDate
  expired
}

class TradeController
class TradeService
class TradeRepository
class TradeEventRepository
class ValidationService
class KafkaProducer

TradeController --> TradeService
TradeService --> TradeRepository
TradeService --> TradeEventRepository
TradeService --> ValidationService
TradeService --> KafkaProducer

@enduml

4.3 Architecture Diagram
@startuml

node "Spring Boot App" {
  component Controller
  component Service
  component Validation
}

database "SQL DB" {
  component TradeStore
}

database "MongoDB" {
  component TradeEvents
}

queue "Kafka" {
  component TradeTopic
}

Controller --> Service
Service --> Validation
Service --> TradeStore
Service --> TradeEvents
Service --> TradeTopic

@enduml

🛠️ 5️⃣ Technology Stack
Layer	Technology
Backend	Spring Boot 3
Language	Java 17
Build Tool	Maven
SQL DB	H2 (In-Memory)
NoSQL	MongoDB
Streaming	Apache Kafka
Testing	JUnit 5, Mockito
CI/CD	GitHub Actions
Security Scan	OWASP Dependency Check
Scheduling	Spring Scheduler
Diagrams	PlantUML
📂 6️⃣ Project Structure
src/main/java/com/dws/trade_store

├── controller/
├── service/
├── repository/
├── mongo/
├── kafka/
├── validation/
├── scheduler/
├── exception/
└── model/

▶️ 7️⃣ Execution Steps
7.1 Run Application
mvn spring-boot:run


App runs on:

http://localhost:8082

7.2 Health Check
GET /trades/ping

7.3 Create Trade

POST /trades

Request Body

{
  "tradeId": "T1",
  "version": 1,
  "createdDate": "2026-02-07",
  "maturityDate": "2030-12-31",
  "expired": false
}

🗄️ 8️⃣ H2 Database Access

URL:

http://localhost:8082/h2


JDBC:

jdbc:h2:mem:trade-db

🍃 9️⃣ MongoDB Setup

Start MongoDB:

mongod


Collection:

tradeEvents


Purpose: Stores full trade audit logs.

📡 🔟 Kafka Setup
Start Kafka (KRaft Mode)
cd kafka_2.13-3.9.1

bin\windows\kafka-storage.bat random-uuid

bin\windows\kafka-storage.bat format -t <UUID> -c config\kraft\server.properties

bin\windows\kafka-server-start.bat config\kraft\server.properties

Create Topic
bin\windows\kafka-topics.bat --create \
--topic trade-topic \
--bootstrap-server localhost:9092 \
--partitions 1 \
--replication-factor 1

Start Consumer
bin\windows\kafka-console-consumer.bat \
--topic trade-topic \
--from-beginning \
--bootstrap-server localhost:9092


Publishing a trade will stream events to this consumer.

🔁 1️⃣1️⃣ CI/CD Pipeline

Implemented using GitHub Actions.

Pipeline stages:

Build

Unit Tests

Security Scan

Pipeline file location:

.github/workflows/ci.yml

🔐 1️⃣2️⃣ Security Scan

Integrated tool:

OWASP Dependency Check

Run manually:

mvn dependency-check:check


Detects vulnerable libraries and CVEs.

🧪 1️⃣3️⃣ Testing (TDD)

JUnit coverage includes:

Valid trade save

Lower version rejection

Maturity validation

Expiry logic

Run tests:

mvn test

⏰ 1️⃣4️⃣ Scheduler

A daily scheduled job:

Checks maturity dates

Marks eligible trades as expired

Implements automated lifecycle governance.

🔄 1️⃣5️⃣ Event Flow Summary
REST → Validation → SQL → Mongo → Kafka

System	Role
SQL	Golden state
MongoDB	Audit trail
Kafka	Streaming distribution
🚀 1️⃣6️⃣ Future Enhancements

Snowflake analytics sink

Schema registry integration

Kafka partition scaling

Redis caching layer

Kubernetes deployment

OAuth2 / OIDC security

🏁 1️⃣7️⃣ Conclusion

This system demonstrates:

Enterprise trade ingestion

Event-driven streaming

Dual persistence architecture

Automated lifecycle controls

DevSecOps pipeline integration

It reflects modern investment data platform engineering practices.