package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

public final class Answer implements Action {

    private final String answer;

    public Answer(String[] actionData) {
        if (actionData.length < 1) throw new IllegalArgumentException("Answer requires 1 argument");
        if (actionData.length == 1) {
            this.answer = actionData[0];
        } else {
            this.answer = String.join(":", actionData);
        }
    }

    @Override
    public String applyTo(MarkedPage markedPage) {
        return answer;
    }
}
