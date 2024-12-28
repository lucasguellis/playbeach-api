# Spring API for Tournament Subscription and Payment for PlayBeach project app

This Spring-based API allows users to subscribe to tournaments and make secure payments using credit cards. Stripe is used as the payment processing service. The API includes a Docker Compose setup for local development with a PostgreSQL database.

## Features
- User registration and authentication
- Tournament subscription management
- Credit card payments through Stripe
- Local development environment using Docker Compose with PostgreSQL

---

## Requirements

### Prerequisites
- Java 21 or higher
- Gradle 8.0 or higher
- Docker and Docker Compose

---

## Setup

### Clone the Repository
```bash
git clone https://github.com/lucasguellis/playbeach-api.git
cd playbeach-api
```

### Configure Environment Variables

Create a `.env` file in the root directory to store sensitive information. Replace placeholders with your own values:

```env
# Stripe API keys
STRIPE_SECRET_KEY=your-stripe-api-key
STRIPE_WEBHOOK_SECRET=your-stripe-webhook-secret

# Api secret key
SECRET_KEY=SECRET_KEY
```

### Run Locally with Docker Compose

#### Start the Containers
```bash
docker-compose up postgres
```
This will start a PostgreSQL database and configure it for the API.

### Run the Application
```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

---

## Running Tests
Run the following command to execute tests:
```bash
./gradlew test
```

---

## Deployment
To deploy the application, ensure the environment variables are properly configured for your production environment. Then, build the project:
```bash
./gradlew build
```
Deploy the generated JAR or WAR file to your production server.

---

## Troubleshooting

### Common Issues
- **Database Connection:** Ensure the PostgreSQL database is running and the credentials in `.env` are correct.
- **Stripe Configuration:** Verify that your Stripe API keys are valid and the webhook endpoint is properly configured.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [Stripe](https://stripe.com/)
- [Docker](https://www.docker.com/)
