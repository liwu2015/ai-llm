package com.liwu.aillm.controller;

import com.liwu.aillm.dto.ChatRequestDTO;
import com.liwu.aillm.dto.ChatResponseDTO;
import com.liwu.aillm.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Chat REST API.
 *
 * @author liwu
 * @date 2026-06-22
 */
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;

    /**
     * Send a chat message.
     *
     * @param request chat request body
     * @return model answer
     */
    @PostMapping
    public ChatResponseDTO chat(@RequestBody ChatRequestDTO request) {
        logger.info("POST /chat");
        return chatService.chat(request);
    }

}
