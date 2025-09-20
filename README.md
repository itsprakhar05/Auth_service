# Auth Service

This project is a Java-based authentication microservice designed to handle user authentication, JWT token management, and user role handling. It is structured for cloud deployment and containerization, making it suitable for modern distributed systems.

## Features
- User authentication and authorization
- JWT token generation and validation
- Refresh token support
- User roles and permissions
- RESTful API endpoints for authentication
- Password reset with token expiration
- Event-driven user info updates
- CloudFormation template for AWS deployment
- Dockerfile for containerization

## Project Structure
```
auth_service/
  app/
    src/main/java/authservice/
      auth/                # JWT filters and user config
      controller/          # REST controllers
      entities/            # Data models
      eventProducer/       # Event publishing
      model/               # DTOs
      repository/          # Data access
      request/             # Request DTOs
      response/            # Response DTOs
      serializer/          # Custom serializers
      service/             # Business logic
      utils/               # Utility classes
    resources/
      application.properties
  cloudformation-template.yaml
  Dockerfile
  services.yml
  gradlew, gradlew.bat
  settings.gradle
  build.gradle
```

## Getting Started

### Prerequisites
- Java 17+ (required)
- Gradle
- Docker (optional, for containerization)
- AWS CLI (optional, for deployment)

### Build and Run
1. **Build the project:**
   ```bash
   ./gradlew build
   ```
2. **Run the service:**
   ```bash
   ./gradlew bootRun
   ```
3. **Run with Docker:**
   ```bash
   docker build -t auth-service .
   docker run -p 8080:8080 auth-service
   ```

### API Endpoints
- `POST /auth/login` — Authenticate user and get JWT
- `POST /auth/refresh` — Refresh JWT token
- `GET /user/info` — Get user information
- `POST /auth/v1/password-reset-request` — Request password reset (generates token, sends email)
- `POST /auth/v1/password-update` — Update password using reset token

## Deployment
- Use `cloudformation-template.yaml` for AWS deployment.
- Configure environment variables in `application.properties`.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.
