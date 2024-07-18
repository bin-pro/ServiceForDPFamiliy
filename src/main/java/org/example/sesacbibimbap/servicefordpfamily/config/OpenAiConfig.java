package org.example.sesacbibimbap.servicefordpfamily.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    @Value("${app.open-ai.api-key}")
    private String apiKey;

    @Value("${app.open-ai.timeout-seconds}")
    private long timeoutSeconds;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(apiKey);
    }
}
