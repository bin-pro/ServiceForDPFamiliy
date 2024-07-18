package org.example.sesacbibimbap.servicefordpfamily.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CounselingDto {
    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class GetResponse{
        private String content;
        private LocalDateTime createdAt;

        @Builder
        public GetResponse(String content, LocalDateTime createdAt) {
            this.content = content != null ? content : "";
            this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        }
    }

    @Data
    @NoArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class GetRequest{
        private String content;

        @Builder
        public GetRequest(String content) {
            this.content = content != null ? content : "";
        }
    }
}
