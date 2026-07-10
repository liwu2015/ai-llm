package com.liwu.aillm.controller;

import com.liwu.aillm.service.CollectionAgentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 17:11 2026/7/10
 */
@RestController
public class CollectionAgentController {

    @Resource
    private CollectionAgentService collectionAgentService;

    @GetMapping("/agent/collection/chat")
    public String chat(@RequestParam String question) {
        return collectionAgentService.chat(question);
    }
}
