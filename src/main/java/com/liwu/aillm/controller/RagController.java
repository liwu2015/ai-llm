package com.liwu.aillm.controller;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 12:51 2026/7/11
 */
@RestController
public class RagController {

    @Resource
    private OpenAiChatModel chatLanguageModel;

    @Resource
    private EmbeddingModel embeddingModel;
    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @GetMapping("/rag/chat")
    public String ragChat(@RequestParam String question) {
        // 1. 用户问题向量化检索知识库
        var queryVector = embeddingModel.embed(question).content();
        EmbeddingSearchRequest searchReq = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryVector)
                .maxResults(3)
                .minScore(0.6)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchReq);
        List<EmbeddingMatch<TextSegment>> matches = searchResult.matches();

        // 拼接检索到的知识库上下文
        String context;
        if (matches.isEmpty()) {
            context = "暂无相关业务规则";
        } else {
            context = matches.stream()
                    .map(EmbeddingMatch::embedded)
                    .map(TextSegment::text)
                    .collect(Collectors.joining("\n---\n"));
        }

        // 2. 构造Prompt，限制只能根据知识库回答
        String systemPrompt = """
                你是催收业务客服，只能根据下面【知识库规则】回答用户问题，禁止编造政策。
                知识库规则：
                %s
                如果知识库无相关内容，直接回复暂无相关规定。
                """.formatted(context);

        // 3. 调用DeepSeek生成答案
        AiMessage response = chatLanguageModel.chat(
                new SystemMessage(systemPrompt),
                new UserMessage(question)
        ).aiMessage();

        return response.text();
    }
}
