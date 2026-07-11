package com.liwu.aillm.service;

import com.liwu.aillm.tool.CollectionQueryTool;
import com.liwu.aillm.tool.RagRetrieveTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 17:09 2026/7/10
 */
@Service
public class CollectionAgentService {

    private final CollectionSupportAgent agent;

    public CollectionAgentService(CollectionQueryTool collectionQueryTool, RagRetrieveTool ragRetrieveTool, ChatModel chatModel) {
        String systemPrompt = """
                你是贷后催收智能坐席客服，严格遵守规则：
                1. 用户提供客户编号查欠款、逾期信息，调用queryDebtInfo；
                2. 用户询问外呼、短信催收记录调用queryCollectionLog；
                3. 用户咨询分期、罚息减免、催收合规政策，**必须先调用searchKnowledge检索知识库**；
                4. 手机号、身份证必须脱敏，绝不明文展示；
                5. 所有业务政策以知识库检索结果为准，禁止编造规则；
                6. 多轮对话记住客户编号，后续提问无需重复输入；
                7. 回答简洁口语化，面向催收工作人员提供辅助查询。
                """;

        agent = AiServices.builder(CollectionSupportAgent.class)
                .chatModel(chatModel)
                .systemMessage(systemPrompt)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .tools(collectionQueryTool, ragRetrieveTool)
                .build();
    }

    public String chat(String userMsg) {
        return agent.chat(userMsg);
    }

    public interface CollectionSupportAgent {
        String chat(String userQuestion);
    }
}
