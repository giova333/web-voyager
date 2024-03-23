package com.webvoyager.lmm;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;

import java.util.List;

class LMMRequestSender {

    private final LMMInteractionLogger lmmInteractionLogger = new LMMInteractionLogger();

    private final OpenAiChatModel model = OpenAiChatModel.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .modelName(OpenAiChatModelName.GPT_4_VISION_PREVIEW)
            .build();

    String send(List<ChatMessage> chatMessages) {
        lmmInteractionLogger.logRequest(chatMessages);

        var response = model.generate(chatMessages);

        lmmInteractionLogger.logResponse(response);

        return response.content().text();
    }

}
