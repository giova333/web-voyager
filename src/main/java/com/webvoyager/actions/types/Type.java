package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

import static java.lang.String.format;

public final class Type implements Action {

    private final int webElementIndex;
    private final String text;

    public Type(String[] actionData) {
        if (actionData.length != 2) {
            throw new IllegalArgumentException("Type action should have 2 parameters");
        }
        this.webElementIndex = Integer.parseInt(actionData[0]);
        this.text = actionData[1];
    }

    @Override
    public String applyTo(MarkedPage markedPage) {
        markedPage.typeAndSubmit(webElementIndex, text);
        return format("Typed %s and submitted", text);
    }
}
