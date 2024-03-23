package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

import static java.lang.String.format;

public final class Click implements Action {

    private final int webElementIndex;

    public Click(String[] actionData) {
        if (actionData.length != 1) throw new IllegalArgumentException("ClickAction requires 1 argument");
        this.webElementIndex = Integer.parseInt(actionData[0]);
    }

    @Override
    public String applyTo(MarkedPage markedPage) {
        markedPage.click(webElementIndex);
        return format("Click %d", webElementIndex);
    }
}
