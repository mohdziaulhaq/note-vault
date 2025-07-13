# NOTE-VAULT-BACKEND

*Secure Backend for Seamless Note Management*

![GitHub last commit](https://img.shields.io/badge/last%20commit-today-blue)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring--Boot-3.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-Authentication-orange)

## Overview

Note Vault Spring is a secure and efficient backend API service built with Spring Boot. It powers the note-vault-react frontend with features such as JWT authentication, role-based access control, note CRUD operations, and optional 2FA support. The project follows RESTful principles and is optimized for production deployment using Docker.

## Technologies Used

* Spring Boot (Security, JPA, Web)
* MySQL
* Hibernate (JPA Implementation)
* JWT Authentication
* 2FA Integration (Optional)
* Maven
* Docker

## Why note-vault?

This backend provides all essential features to support a modern note-taking app securely and reliably:

* 🔒 **Secure Auth**: JWT authentication with password encryption and optional 2FA
* 📄 **Note CRUD**: Create, view, update, and delete personal notes
* ⚙️ **Role-Based Access**: Admin and user-level access control
* 🚀 **RESTful APIs**: Cleanly structured and easy to consume
* 🐳 **Docker Support**: Easy to deploy anywhere (e.g., Render, AWS, Railway)

## Table of Contents

* [Overview](#overview)
* [Getting Started](#getting-started)

    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
    * [Running the App](#running-the-app)
    * [Docker Deployment](#docker-deployment)
* [API Endpoints](#api-endpoints)
* [Live Demo](#live-demo)
* [Learnings](#learnings)
* [Author](#author)
* [License](#license)
* [Support](#support)

## Getting Started

### Prerequisites

* Java 17
* Maven 3.9+
* MySQL

### Installation

1. **Clone the Repository:**

```bash
git clone https://github.com/mohdziaulhaq/note-vault
cd note-vault
```

2. **Configure the Database:**
   Update `application.properties` or use environment variables:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/notevault
spring.datasource.username=root
spring.datasource.password=yourpassword
```

3. **Package the Application:**

```bash
mvn clean package -DskipTests
```

### Running the App

```bash
java -jar target/note-vault-0.0.1-SNAPSHOT.jar
```

### Docker Deployment

Create an image and deploy:

```dockerfile
# Use Maven to build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Use lightweight JRE
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/note-vault-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "note-vault-0.0.1-SNAPSHOT.jar"]
```

Then build and run:

```bash
docker build -t note-vault-backend .
docker run -p 8080:8080 note-vault-backend
```

## API Endpoints

* `POST /api/auth/public/signin`: Login
* `POST /api/auth/public/signup`: Register new user
* `POST /api/auth/public/verify-2fa-login`: Verify 2FA
* `GET /api/notes`: List notes (authenticated)
* `POST /api/notes`: Create note
* `PUT /api/notes/{id}`: Update note
* `DELETE /api/notes/{id}`: Delete note

## Live Demo

| Environment       | URL                                                                                        |
| ----------------- | ------------------------------------------------------------------------------------------ |
| **Frontend**      | [https://notevault-zia.netlify.app/](https://notevault-zia.netlify.app/)                   |
| **Backend / API** | [https://notevault-backend-6bly.onrender.com](https://notevault-backend-6bly.onrender.com) |

Use the above links to test the app UI or hit backend APIs via Postman or curl.

## Learnings

This backend project helped me:

* Implement secure JWT token authentication and 2FA integration
* Use Spring Security with custom `UserDetailsService`
* Define and enforce role-based route access
* Structure a production-ready Spring Boot project
* Manage MySQL connection in production environments (via Docker & Render)
* Handle CORS for cross-origin communication with frontend
* Write clean, maintainable REST APIs with exception handling

## Author

**Zia Ul Haq Mohammed**

* 🌐 Portfolio: [mohdziaulhaq.netlify.app](https://mohdziaulhaq.netlify.app)
* 💼 LinkedIn: [linkedin.com/in/mohdziaulhaq](https://www.linkedin.com/in/mohdziaulhaq/)
* 📧 Email: [mohdziaulhaq123@gmail.com](mailto:mohdziaulhaq123@gmail.com)
* 🐱 GitHub: [@mohdziaulhaq](https://github.com/mohdziaulhaq)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

Have questions, issues, or feedback? [Open an issue on GitHub](https://github.com/mohdziaulhaq/note-vault-spring/issues).

---

*Built with ❤️ using Spring Boot and Docker*
