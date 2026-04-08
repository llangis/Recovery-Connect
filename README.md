# Recovery Connect Web Application

![Recovery Connect Logo](recovery-connect-logo.png)

A fellowship recovery tracking platform built with **Java 21 & Spring Boot** for browsing and managing recovery content across AA, NA, and CA programs.

---

## 💻 Project Overview

**Recovery Connect** helps individuals in recovery browse, manage, and track the 12 Steps, 12 Traditions, Promises, and Readings across three fellowship programs: **AA** (Alcoholics Anonymous), **NA** (Narcotics Anonymous), and **CA** (Cocaine Anonymous).
The app supports full CRUD operations, fellowship-specific filtering, user authentication, and ships with pre-loaded seed data.

**Catch Phrase:** *"One Step at a Time"*

---

## 🚀 This Week's Focus

- Database Design
- Onion Architecture Setup
- User Authentication System (Spring Security)
- Fellowship Picker Landing Page
- Steps & Traditions Management
- Seed Data Population

---

## 👤 Development Team – MVCode

<img src="mvcode-logo.png" alt="MVCode Logo" width="200"/>

| Name | Role |
|---|---|
| **Luc Langis** | Full-Stack Lead & Client Liaison |

---

## ⚙️ Tech Stack

- Java 21
- Spring Boot 3.2.5
- Spring Security
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL 8
- Maven (multi-module)
- Bootstrap 5

---

## 📁 Project Structure

```
recovery-connect/
├── common/                          # Shared utilities
├── auth/                            # Users & Spring Security
├── fellowship/
│   ├── domain/                      # DTOs, FellowshipType enum, Result<T>, ValidationError
│   ├── application-api/             # Service & repository interfaces
│   ├── application/                 # Service implementations, validators
│   ├── persistence/                 # JPA entities, repos, mappers, adapters
│   └── web/                         # Controllers & Thymeleaf templates
└── app-web/                         # Spring Boot app, config, seed SQL
```

---

## 🧅 Architecture

Multi-module Maven project using **onion/clean architecture** — dependencies always point inward.

| Layer | Purpose |
|---|---|
| **domain** | DTOs (Step, Tradition, Promise, Reading), `FellowshipType` enum, `Result<T>`, `ValidationError` |
| **application-api** | Service and repository interfaces |
| **application** | `ResultImpl`, service implementations, `BaseValidator`, `CommonValidator` |
| **persistence** | JPA entities, Spring Data repositories, entity-to-DTO mappers, adapters |
| **web** | 4 controllers + 17 Thymeleaf templates — CRUD views, fellowship switcher, layout fragments |
| **app-web** | Boot entry point, landing page, `application.properties`, `data.sql` seed data |

---

## 📊 Seed Data

The app ships with pre-loaded content via `data.sql`:

| Content | Count | Details |
|---|---|---|
| Steps | 36 | 12 per fellowship (AA, NA, CA) |
| Promises | 12 | AA Promises |
| Traditions | 36 | 12 per fellowship (AA, NA, CA) |
| Readings | 15 | 4 AA, 7 NA, 4 CA |

---

## 📦 Getting Started

### Prerequisites

- Java 21+
- Maven 3.9+
- MySQL 8+

### Database Setup

```sql
CREATE DATABASE recovery_db;
```

### Configuration

Update `app-web/src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/recovery_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Build & Run

```bash
mvn clean install
cd app-web
mvn spring-boot:run
```

Open your browser at [http://localhost:8080](http://localhost:8080)

---

## 🎯 How It Works

1. Land on the **fellowship picker** — choose AA, NA, or CA
2. Browse the **12 Steps** for your chosen fellowship
3. Read through **Traditions**, **Promises**, and **Readings**
4. Use the CRUD interface to add, edit, or remove content
5. Switch between fellowships at any time

---

## 🙋 Author

**Luc Langis**
Software Development Student — NBCC Moncton
[GitHub](https://github.com/llangis)

---

## 📄 License

MIT
