package com.liwu.aillm.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * LangChain4j chat model configuration.
 *
 * @author liwu
 * @date 2026-06-22
 */
@Configuration
public class LangChain4jConfig {

    private static final Logger logger = LoggerFactory.getLogger(LangChain4jConfig.class);

    @Bean
    public ChatModel chatModel(
            @Value("${spring.ai.deepseek.api-key}") String apiKey,
            @Value("${spring.ai.deepseek.base-url}") String baseUrl,
            @Value("${spring.ai.deepseek.chat.model}") String modelName,
            @Value("${spring.ai.deepseek.chat.temperature}") Double temperature,
            @Value("${langchain4j.chat.timeout-seconds}") Long timeoutSeconds,
            @Value("${langchain4j.chat.log-requests}") Boolean logRequests,
            @Value("${langchain4j.chat.log-responses}") Boolean logResponses
    ) {
        logger.info("Initializing ChatModel, modelName={}, logRequests={}, logResponses={}",
                modelName, logRequests, logResponses);
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(temperature)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .logRequests(logRequests)
                .logResponses(logResponses)
                .build();
    }

}
