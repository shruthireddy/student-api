# 🎓 Student Management System API

A professional Spring Boot REST API for student management, containerized with Docker.

## 🚀 Quick Start
1. **Clone:** `git clone https://github.com/shruthireddy/student-api.git`
2. **Run:** `docker compose up --build`

## 📖 Features
- **API Versioning:** All endpoints are hosted under `/api/v1/` for future-proofing.
- **Auto-Documentation:** Interactive Swagger UI at `http://localhost:8080/swagger-ui/index.html`.
- **Database:** Persistent MySQL 8.0 storage via Docker volumes.
- **Validation:** Strict data integrity checks for Age, Email, and Name.
- **DTO Pattern:** Clean separation between Database Entities and API Responses.

## 🛠 Tech Stack
- Java 21, Spring Boot 3.3.4, MySQL, Docker, Swagger.