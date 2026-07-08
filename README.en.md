# AI-LLM

A lightweight AI large language model conversational service integrated with Spring Boot and LangChain4j.

## Project Overview

AI-LLM is a lightweight Java backend service that integrates the DeepSeek large language model API via the LangChain4j framework, providing a simple RESTful interface for intelligent conversations.

## Technology Stack

- **Java 17+**
- **Spring Boot 3.x**
- **LangChain4j** - Java AI framework
- **DeepSeek API** - Large language model provider

## Project Structure

```
com.liwu.aillm
├── config/               # Configuration classes
│   ├── LangChain4jConfig.java    # LangChain4j ChatModel configuration
│   └── ModelConfiguration.java   # ChatClient configuration
├── controller/          # Controllers
│   └── ChatController.java       # Conversation REST API
├── dto/                 # Data Transfer Objects
│   ├── ChatRequestDTO.java       # Request DTO
│   └── ChatResponseDTO.java      # Response DTO
├── handler/             # Exception handling
│   └── GlobalExceptionHandler.java
├── service/            # Service layer
│   ├── ChatService.java          # Conversation service interface
│   └── impl/ChatServiceImpl.java # Conversation service implementation
└── AiLlmApplication.java         # Main application class
```

## Configuration

Configure the following parameters in `application.properties`:

```properties
# DeepSeek API configuration
spring.ai.deepseek.api-key=your-api-key
spring.ai.deepseek.base-url=https://api.deepseek.com
spring.ai.deepseek.chat.model=deepseek-chat
spring.ai.deepseek.chat.temperature=0.7

# LangChain4j configuration
langchain4j.chat.timeout-seconds=60
langchain4j.chat.log-requests=true
langchain4j.chat.log-responses=true
```

## API Usage

### Conversation Endpoint

**Endpoint:** `POST /chat`

**Request Body:**
```json
{
  "message": "Hello, please introduce yourself"
}
```

**Response:**
```json
{
  "answer": "Hello! I am..."
}
```

**Example:**

```bash
curl -X POST http://localhost:8080/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello, please introduce yourself"}'
```

## Running the Project

1. Clone the project
2. Configure the DeepSeek API key in `application.properties`
3. Run the project:

```bash
./mvnw spring-boot:run
```

Or build and run manually:

```bash
./mvnw clean package
java -jar target/ai-llm-0.0.1-SNAPSHOT.jar
```

## Testing

Run unit tests:

```bash
./mvnw test
```

## License

MIT License