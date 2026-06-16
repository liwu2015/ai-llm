# AI-LLM Intelligent Conversation Service

An AI large language model integration service built on Spring Boot and LangChain4j, supporting DeepSeek API calls and providing a simple RESTful interface for intelligent conversations.

## Project Overview

This project is a lightweight AI conversation service framework that leverages the convenience of Spring Boot and the powerful AI integration capabilities of LangChain4j to achieve seamless integration with the DeepSeek large language model. Through a REST API, developers can easily integrate AI conversation functionality into various applications.

## Technology Stack

- **Spring Boot 3.x** - Application framework
- **LangChain4j** - AI language model integration library
- **DeepSeek API** - Large language model service provider
- **Maven** - Project build tool
- **Java 17+** - Development language

## Core Features

### 1. Intelligent Conversation API
Provides a unified REST API endpoint to send text messages and receive AI responses.

### 2. Flexible Model Configuration
Easily configure DeepSeek API keys, endpoints, and model parameters via configuration files.

### 3. Optimized Dependency Injection
Uses Spring’s dependency injection mechanism to manage ChatModel and ChatClient, facilitating unit testing and extensibility.

## Project Structure

```
ai-llm/
├── src/
│   ├── main/
│   │   ├── java/com/liwu/aillm/
│   │   │   ├── AiLlmApplication.java      # Spring Boot application class
│   │   │   ├── config/                      # Configuration package
│   │   │   │   ├── LangChain4jConfig.java   # LangChain4j configuration
│   │   │   │   └── ModelConfiguration.java  # Model client configuration
│   │   │   ├── controller/                  # Controller package
│   │   │   │   └── ChatController.java      # Conversation REST API
│   │   │   └── service/                      # Service package
│   │   │       └── AgentExample.java       # Agent example
│   │   └── resources/
│   │       └── application.properties       # Application configuration
│   └── test/
│       └── java/com/liwu/aillm/
│           └── AiLlmApplicationTests.java    # Unit tests
├── pom.xml                                   # Maven configuration
└── mvnw                                      # Maven wrapper
```

## Quick Start

### Prerequisites

- JDK 17 or higher
- Maven 3.6+

### Configuration Steps

1. Clone the project locally

```bash
git clone https://gitee.com/liwu2015/ai-llm.git
cd ai-llm
```

2. Configure DeepSeek API Key

Edit `src/main/resources/application.properties` and add the following configuration:

```properties
# DeepSeek API Configuration
spring.ai.deepseek.api-key=your-api-key-here
spring.ai.deepseek.base-url=https://api.deepseek.com
spring.ai.deepseek.chat.model=deepseek-chat
```

3. Build and Run

```bash
./mvnw spring-boot:run
```

Or build first, then run:

```bash
./mvnw clean package
java -jar target/ai-llm-0.0.1-SNAPSHOT.jar
```

## Usage Instructions

### Calling the Conversation API

Send a POST request to the `/ai/chat` endpoint:

```bash
curl -X POST http://localhost:8080/ai/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello, please introduce yourself"}'
```

### Response Format

```json
{
  "reply": "Hello! I am an AI assistant developed based on the DeepSeek model..."
}
```

## Configuration Reference

| Configuration Key | Description | Example Value |
|-------------------|-------------|---------------|
| spring.ai.deepseek.api-key | DeepSeek API key | sk-xxxxxxx |
| spring.ai.deepseek.base-url | API endpoint URL | https://api.deepseek.com |
| spring.ai.deepseek.chat.model | Model name to use | deepseek-chat |

## Extension Development

### Adding a New AI Model

1. Create a new configuration class under the `config` package.
2. Implement the corresponding ChatModel Bean.
3. Add related configuration in `application.properties`.

### Customizing Conversation Logic

Create new service classes under the `service` package to implement complex conversation logic and business processing.

## License

This project is intended solely for learning and reference purposes. Please comply with DeepSeek’s terms of use and privacy policy.

## Contribution Guidelines

Pull requests and issues are welcome to help improve this project.

## Contact

- Project URL: https://gitee.com/liwu2015/ai-llm