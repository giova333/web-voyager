package com.webvoyager.lmm;

import com.webvoyager.utils.ResourceLoader;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
class LMMRequestSender {

    private final LMMInteractionLogger lmmInteractionLogger = new LMMInteractionLogger();

    private final OpenAiChatModel model = getOpenaiChatModel();

    String send(List<ChatMessage> chatMessages) {
        lmmInteractionLogger.logRequest(chatMessages);

        var response = model.generate(chatMessages);

        lmmInteractionLogger.logResponse(response);

        return response.content().text();
    }

    private static OpenAiChatModel getOpenaiChatModel() {
        var config = ResourceLoader.loadResource("model.properties");
        var properties = parseProperties(config);

        var modelType = (String) properties.get("lmm.model.type");

        var modelName = switch (modelType) {
            case "gpt-4o" -> OpenAiChatModelName.GPT_4_O;
            case "gpt-4-vision-preview" -> OpenAiChatModelName.GPT_4_VISION_PREVIEW;
            default -> throw new IllegalArgumentException("Unsupported model type: " + modelType);
        };
        log.info("Using OpenAI model: {}", modelName);

        return OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName(modelName)
                .build();
    }

    private static Properties parseProperties(String config) {
        var properties = new Properties();
        Arrays.stream(config.split("\n"))
                .map(line -> line.split("="))
                .forEach(pair -> properties.setProperty(pair[0], pair[1]));

        return properties;
    }

}
