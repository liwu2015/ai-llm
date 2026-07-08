package com.liwu.aillm.service.impl;

import com.liwu.aillm.dto.ChatRequestDTO;
import com.liwu.aillm.dto.ChatResponseDTO;
import com.liwu.aillm.service.ChatService;
import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Chat service implementation.
 *
 * @author liwu
 * @date 2026-06-22
 */
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatModel chatModel;

    @Override
    public ChatResponseDTO chat(ChatRequestDTO request) {
        if (request == null || request.getMessage() == null || request.getMessage().isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
        logger.info("Processing chat request, messageLength={}", request.getMessage().length());
        String answer = chatModel.chat(request.getMessage());
        return new ChatResponseDTO(answer);
    }

}
