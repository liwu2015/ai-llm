

基于提供的代码结构和项目信息，我来为您创建一份详尽的 README 文档。

```markdown
# AI-LLM 智能对话服务

一个基于 Spring Boot 和 LangChain4j 构建的 AI 大语言模型集成服务，支持 DeepSeek API 调用，提供简洁的 RESTful 接口用于智能对话。

## 项目简介

本项目是一个轻量级的 AI 对话服务框架，利用 Spring Boot 的便捷性和 LangChain4j 强大的 AI 能力整合，实现与 DeepSeek 大语言模型的无缝对接。通过 REST API 的方式，开发者可以轻松地将 AI 智能对话功能集成到各类应用中。

## 技术栈

- **Spring Boot 3.x** - 应用框架
- **LangChain4j** - AI 语言模型集成库
- **DeepSeek API** - 大语言模型服务提供商
- **Maven** - 项目构建工具
- **Java 17+** - 开发语言

## 核心功能

### 1. 智能对话接口
提供统一的 REST API 端点，支持发送文本消息并获取 AI 回复。

### 2. 灵活的模型配置
通过配置文件轻松配置 DeepSeek API 的密钥、端点和模型参数。

### 3. 依赖注入优化
使用 Spring 的依赖注入机制管理 ChatModel 和 ChatClient，便于单元测试和扩展。

## 项目结构

```
ai-llm/
├── src/
│   ├── main/
│   │   ├── java/com/liwu/aillm/
│   │   │   ├── AiLlmApplication.java      # Spring Boot 启动类
│   │   │   ├── config/                      # 配置包
│   │   │   │   ├── LangChain4jConfig.java   # LangChain4j 配置
│   │   │   │   └── ModelConfiguration.java  # 模型客户端配置
│   │   │   ├── controller/                  # 控制器包
│   │   │   │   └── ChatController.java      # 对话 REST 接口
│   │   │   └── service/                      # 服务包
│   │   │       └── AgentExample.java       # Agent 示例
│   │   └── resources/
│   │       └── application.properties       # 应用配置
│   └── test/
│       └── java/com/liwu/aillm/
│           └── AiLlmApplicationTests.java    # 单元测试
├── pom.xml                                   # Maven 配置
└── mvnw                                      # Maven 包装器
```

## 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6+

### 配置步骤

1. 克隆项目到本地

```bash
git clone https://gitee.com/liwu2015/ai-llm.git
cd ai-llm
```

2. 配置 DeepSeek API 密钥

编辑 `src/main/resources/application.properties` 文件，添加以下配置：

```properties
# DeepSeek API 配置
spring.ai.deepseek.api-key=your-api-key-here
spring.ai.deepseek.base-url=https://api.deepseek.com
spring.ai.deepseek.chat.model=deepseek-chat
```

3. 编译运行

```bash
./mvnw spring-boot:run
```

或者先打包再运行：

```bash
./mvnw clean package
java -jar target/ai-llm-0.0.1-SNAPSHOT.jar
```

## 使用说明

### 调用对话接口

发送 POST 请求到 `/ai/chat` 端点：

```bash
curl -X POST http://localhost:8080/ai/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "你好，请介绍一下自己"}'
```

### 响应格式

```json
{
  "reply": "你好！我是基于 DeepSeek 模型开发的 AI 助手..."
}
```

## 配置说明

| 配置项 | 说明 | 示例值 |
|--------|------|--------|
| spring.ai.deepseek.api-key | DeepSeek API 密钥 | sk-xxxxxxx |
| spring.ai.deepseek.base-url | API 端点地址 | https://api.deepseek.com |
| spring.ai.deepseek.chat.model | 使用的模型名称 | deepseek-chat |

## 扩展开发

### 添加新的 AI 模型

1. 在 `config` 包下创建新的配置类
2. 实现对应的 ChatModel Bean
3. 在 `application.properties` 中添加相关配置

### 自定义对话逻辑

可以在 `service` 包下创建新的服务类，实现复杂的对话逻辑和业务处理。

## 许可证

本项目仅供学习和参考，请遵守 DeepSeek 的使用条款和隐私政策。

## 贡献指南

欢迎提交 Issue 和 Pull Request 来完善项目。

## 联系方式

- 项目地址：https://gitee.com/liwu2015/ai-llm
```

---

**注意**：请根据实际的项目需求和 DeepSeek API 的使用条款修改和调整配置。本 README 基于提供的代码结构创建，如有不准确之处请及时指正。