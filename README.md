# rabbitmq-microservice
Extremely simple Spring Boot microservice using RabbitMQ with the sole objective of learning more about RabbitMQ messaging

## Requirements

- [Java 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Gradle](https://gradle.org/)

## Getting Started

1. Clone the repository
2. Navigate to each project repository
3. Build the project using Maven:

```
./mvnw clean install
```

4. Run the project using Maven wrapper:

```
./mvnw spring-boot:run
```

5. The application will start on http://localhost:8080, http://localhost:8081, http://localhost:8082

## Usage

- You will also need the database installed on your machine and set the connection vars on the application.properties or application.yml
