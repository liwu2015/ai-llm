
# AI-LLM

基于 Spring Boot 和 LangChain4j 集成的 AI 大语言模型对话服务。

## 项目简介

AI-LLM 是一个轻量级的 Java 后端服务，通过 LangChain4j 框架集成了 DeepSeek 大语言模型 API，提供简洁的 RESTful 接口用于智能对话。

## 技术栈

- **Java 17+**
- **Spring Boot 3.x**
- **LangChain4j** - Java AI 框架
- **DeepSeek API** - 大语言模型提供商

## 项目结构

```
com.liwu.aillm
├── config/               # 配置类
│   ├── LangChain4jConfig.java    # LangChain4j ChatModel 配置
│   └── ModelConfiguration.java   # ChatClient 配置
├── controller/          # 控制器
│   └── ChatController.java       # 对话 REST 接口
├── dto/                 # 数据传输对象
│   ├── ChatRequestDTO.java       # 请求 DTO
│   └── ChatResponseDTO.java      # 响应 DTO
├── handler/             # 异常处理
│   └── GlobalExceptionHandler.java
├── service/            # 服务层
│   ├── ChatService.java          # 对话服务接口
│   └── impl/ChatServiceImpl.java # 对话服务实现
└── AiLlmApplication.java         # 启动类
```

## 配置说明

在 `application.properties` 中配置以下参数：

```properties
# DeepSeek API 配置
spring.ai.deepseek.api-key=your-api-key
spring.ai.deepseek.base-url=https://api.deepseek.com
spring.ai.deepseek.chat.model=deepseek-chat
spring.ai.deepseek.chat.temperature=0.7

# LangChain4j 配置
langchain4j.chat.timeout-seconds=60
langchain4j.chat.log-requests=true
langchain4j.chat.log-responses=true
```

## API 使用

### 对话接口

**请求地址：** `POST /chat`

**请求体：**
```json
{
  "message": "你好，请介绍一下你自己"
}
```

**响应：**
```json
{
  "answer": "你好！我是..."
}
```

**示例：**

```bash
curl -X POST http://localhost:8080/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "你好，请介绍一下你自己"}'
```

## 运行项目

1. 克隆项目
2. 配置 `application.properties` 中的 DeepSeek API Key
3. 运行项目：

```bash
./mvnw spring-boot:run
```

或先打包再运行：

```bash
./mvnw clean package
java -jar target/ai-llm-0.0.1-SNAPSHOT.jar
```

## 测试

运行单元测试：

```bash
./mvnw test
```

## 许可证

MIT License