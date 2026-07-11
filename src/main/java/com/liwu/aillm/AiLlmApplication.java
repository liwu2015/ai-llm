package com.liwu.aillm;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liwu.aillm.mapper")
public class AiLlmApplication implements CommandLineRunner {

    @Resource
    private EmbeddingStoreIngestor ingestor;

    public static void main(String[] args) {
        SpringApplication.run(AiLlmApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            String rule1 = """
                罚息减免规则：
                1. 逾期≤7天：无罚息减免；
                2. 逾期8~15天：一次性结清可减免5%罚息；
                3. 逾期>15天：无任何罚息减免政策；
                """;
            String rule2 = """
                催收合规话术规范：
                1. 禁止威胁、恐吓客户；
                2. 禁止联系客户第三方亲友；
                3. 仅可在早9点-晚20点外呼；
                """;
            String rule3 = """
                分期方案规则：
                分3期手续费0.8%/期，分6期1.2%/期，分期后不可申请罚息减免。
                """;

            Document doc1 = Document.from(rule1);
            Document doc2 = Document.from(rule2);
            Document doc3 = Document.from(rule3);
            ingestor.ingest(doc1, doc2, doc3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
