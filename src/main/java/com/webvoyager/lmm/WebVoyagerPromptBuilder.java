package com.webvoyager.lmm;

import com.webvoyager.page.MarkedPage;
import com.webvoyager.page.WebElement;
import com.webvoyager.actions.ActionHistory;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.List;
import java.util.Map;

import static com.webvoyager.utils.ResourceLoader.loadResource;

class WebVoyagerPromptBuilder {

    private static final String WEB_VOYAGER_SYSTEM_PROMPT_PATH = "prompts/web-voyager-system-prompt.txt";
    private static final String PREVIOUS_ACTION_SYSTEM_PROMPT_PATH = "prompts/previous-action-system-prompt.txt";
    private static final String USER_MESSAGE_PROMPT_PATH = "prompts/user-prompt.txt";

    private final WebElementsToTextFormatter webElementsToTextFormatter = new WebElementsToTextFormatter();


    List<ChatMessage> buildPrompt(String question,
                                  MarkedPage markedPage,
                                  ActionHistory history) {
        var webVoyagerSystemMessage = buildWebVoyagerSystemMessage();
        var actionHistorySystemMessage = buildActionHistorySystemMessage(history);
        var userMessage = buildUserMessage(question, markedPage.elements(), markedPage.asBase64());

        return List.of(webVoyagerSystemMessage, actionHistorySystemMessage, userMessage);
    }

    private ChatMessage buildUserMessage(String question, List<WebElement> webElements, String imageBase64) {
        var userPromptTemplate = loadResource(USER_MESSAGE_PROMPT_PATH);
        var userPrompt = new PromptTemplate(userPromptTemplate)
                .apply(Map.of(
                        "question", question,
                        "webElements", webElementsToTextFormatter.formatElements(webElements)));

        return new UserMessage(new TextContent(userPrompt.text()), new ImageContent(imageBase64, "image/png"));
    }

    private ChatMessage buildActionHistorySystemMessage(ActionHistory history) {
        var actionHistorySystemPromptTemplate = loadResource(PREVIOUS_ACTION_SYSTEM_PROMPT_PATH);
        var promptTemplate = new PromptTemplate(actionHistorySystemPromptTemplate).apply(Map.of("actions", history.toString()));
        return promptTemplate.toSystemMessage();
    }

    private ChatMessage buildWebVoyagerSystemMessage() {
        var webVoyagerSystemMessage = loadResource(WEB_VOYAGER_SYSTEM_PROMPT_PATH);
        return new SystemMessage(webVoyagerSystemMessage);
    }
}
