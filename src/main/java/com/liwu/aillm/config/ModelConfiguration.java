package com.liwu.aillm.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring AI model configuration.
 *
 * @author liwu
 * @date 2026-06-22
 */
@Configuration
public class ModelConfiguration {

    @Bean
    public ChatClient chatClient(DeepSeekChatModel model) {
        return ChatClient
                .builder(model)
                .build();
    }

}
