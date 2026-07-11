package com.liwu.aillm.tool;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 17:57 2026/7/10
 */
@Component
public class RagRetrieveTool {

    @Resource
    private EmbeddingModel embeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Tool("根据用户问题检索催收业务知识库，获取罚息、分期、合规催收规则，回答业务政策类问题必须调用本工具")
    public String searchKnowledge(String question) {
        // 将用户问题转为向量，检索top3相似知识库片段
        List<TextSegment> segments = embeddingStore.search(
                        EmbeddingSearchRequest.builder()
                                .queryEmbedding(embeddingModel.embed(question).content())
                                .maxResults(3)
                                .minScore(0.6)
                                .build()
                ).matches().stream()
                .map(m -> m.embedded())
                .collect(Collectors.toList());

        if (segments.isEmpty()) {
            return "未检索到相关业务规则";
        }
        // 拼接知识库内容返回给大模型
        return segments.stream()
                .map(TextSegment::text)
                .collect(Collectors.joining("\n====分割线====\n"));
    }
}
