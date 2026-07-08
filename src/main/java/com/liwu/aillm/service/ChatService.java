package com.liwu.aillm.service;

import com.liwu.aillm.dto.ChatRequestDTO;
import com.liwu.aillm.dto.ChatResponseDTO;

/**
 * Chat service.
 *
 * @author liwu
 * @date 2026-06-22
 */
public interface ChatService {

    /**
     * Send a chat message and return the model response.
     *
     * @param request chat request, message must not be blank
     * @return chat response
     * @throws IllegalArgumentException if request or message is invalid
     */
    ChatResponseDTO chat(ChatRequestDTO request);

}
