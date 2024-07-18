package org.example.sesacbibimbap.servicefordpfamily.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sesacbibimbap.servicefordpfamily.dto.CounselingDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@RequiredArgsConstructor
@Service @Slf4j
public class CounselingService {
    private final OpenAiService openAiService;

    @Value("${app.open-ai.api-key}")
    private String apiKey;

    @Value("${app.open-ai.model-name}")
    private String modelName;

    private String systemPrompt = "너는 치매 환자 가족들을 돕는 AI 상담가 '돌 봄이'야." +
            " 너는 구석기 시대부터 살아온 지혜로운 돌(stone)이야. 치매에 관한 모든 정보를 알고 있어." +
            " 따뜻하고 공감하는 말투, 부드러운 톤으로 대화해." +
            " 돌봄 가족을 위로하고 격려하며 응원하는 말투를 사용해." +
            " '사용자님'이 해결책을 묻는다면 공감 후 현실적인 해결책을 말해야 해." +
            " 예를 들어 '힘내세요', '정말 잘하고 계시네요', '사용자님은 혼자가 아니에요', '오늘 하루도 고생하셨어요', " +
            "'기분이 울적하다면 기분 전환을 위해 노래를 들어보는 건 어떨까요?' 등의 표현을 사용해. " +
            "전반적으로 경청하고 공감하면서 격려와 지지하는 따뜻하고 친근한 말투를 사용해 가족들의 마음을 이해하고 위로하는 느낌을 줘. " +
            "해결법을 묻는 질문을 한다면 짧은 공감 후 해결책을 우선 제공 해.";

    public CounselingDto.GetResponse getCounselingResponse(CounselingDto.GetRequest request){

        ChatCompletionRequest apiRequest = ChatCompletionRequest.builder()
                .model(modelName)
                .messages(Arrays.asList(
                        new ChatMessage("system", systemPrompt),
                        new ChatMessage("user", request.getContent())
                ))
                .build();
        ChatCompletionResult result = openAiService.createChatCompletion(apiRequest);

        String content = result.getChoices().get(0).getMessage().getContent();

        return CounselingDto.GetResponse.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
