# Notification Scheduler

A modular, hexagonal Spring Boot application to schedule notifications via different channels (e.g. Email, SMS, Push),
following clean architecture and JSON:API standards.

---

## Features

- Schedule notifications via REST API
- JSON:API-compliant responses
- Extensible channel support
- Fully tested with `@WebMvcTest` for controller isolation
- Hexagonal architecture with clear separation of concerns

---

## Tech Stack

- Java 17+
- Spring Boot 3.x
- Maven or Gradle
- Jackson (for JSON serialization)
- JUnit 5 + Mockito

---

## Installation

```bash
git clone https://github.com/your-username/notification-scheduler.git
cd notification-scheduler
./mvnw spring-boot:run
````

---

## API Example

### `POST /api/notifications`

Request:

```json
{
  "message": "Hello there!",
  "recipient": "user@example.com",
  "channel": "EMAIL",
  "scheduledAt": "2025-08-02T14:00:00"
}
```

Response:

```json
{
  "data": {
    "type": "notification",
    "id": "a5b2e3c9-xxxx-xxxx-xxxx-0123456789ab",
    "attributes": {
      "message": "Scheduled successfully"
    }
  }
}
```

---

## Running Tests

```bash
./mvnw test
```

---

## Project Structure

```
.
├── adapters/
│   ├── in/           # Web controllers (input adapters)
│   └── out/          # Output adapters (e.g., web/jsonapi, persistence)
├── application/      # Application services
├── domain/           # Domain model and interfaces
├── NotificationSchedulerApplication.java
└── test/             # Unit and integration tests
```

---

## License

MIT – use it freely in personal or commercial projects.

---

## Author

[andreisima.dev](https://andreisima.dev)

