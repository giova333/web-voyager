package com.webvoyager.lmm;

import com.webvoyager.actions.ActionHistory;
import com.webvoyager.actions.types.Action;
import com.webvoyager.page.MarkedPage;

public class LMMService {

    private final WebVoyagerPromptBuilder webVoyagerPromptBuilder = new WebVoyagerPromptBuilder();
    private final LMMRequestSender lmmRequestSender = new LMMRequestSender();
    private final ActionParser actionParser = new ActionParser();

    public Action askForAction(String task,
                               MarkedPage markedPage,
                               ActionHistory history) {

        var chatMessages = webVoyagerPromptBuilder.buildPrompt(task, markedPage, history);
        var lmmResponse = lmmRequestSender.send(chatMessages);
        return actionParser.parseAction(lmmResponse);
    }
}
