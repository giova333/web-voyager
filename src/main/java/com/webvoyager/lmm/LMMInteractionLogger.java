package com.webvoyager.lmm;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.Content;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.output.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class LMMInteractionLogger {

    void logRequest(List<ChatMessage> chatMessages) {
        var requestLog = new StringBuilder("Request: ");
        for (var chatMessage : chatMessages) {
            requestLog.append("\n").append(createLogMessage(chatMessage));
        }
        log.debug(requestLog.toString());
    }

    void logResponse(Response<AiMessage> response) {
        log.debug("Response: {}", response.content().text());
    }

    private String createLogMessage(ChatMessage chatMessage) {
        var logMessage = new StringBuilder(chatMessage.type().toString()).append(": ");
        if (chatMessage instanceof SystemMessage) {
            logMessage.append(((SystemMessage) chatMessage).text());
        }
        if (chatMessage instanceof UserMessage userMessage) {
            logMessage.append(createUserMessageLog(userMessage));
        }
        return logMessage.toString();
    }

    private String createUserMessageLog(UserMessage userMessage) {
        var userMessageLog = new StringBuilder();
        for (var content : userMessage.contents()) {
            userMessageLog.append("\n").append(processContent(content));
        }
        return userMessageLog.toString();
    }

    private String processContent(Content content) {
        if (content instanceof ImageContent) {
            return ((ImageContent) content).image().base64Data();
        } else {
            return ((TextContent) content).text();
        }
    }
}