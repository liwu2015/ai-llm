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

    /**
     * 催收代理聊天接口 http://127.0.0.1:8080/agent/collection/chat?question=C20260710001逾期12天一次性结清有罚息减免吗
     * @param question 用户问题
     * @return 代理回答
     */
    @GetMapping("/agent/collection/chat")
    public String chat(@RequestParam String question) {
        return collectionAgentService.chat(question);
    }
}
