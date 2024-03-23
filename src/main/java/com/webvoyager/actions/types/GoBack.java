package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

import static java.lang.String.format;

public final class GoBack implements Action {

    @Override
    public String applyTo(MarkedPage markedPage) {
        markedPage.goBack();
        return format("Navigated back a page to %s", markedPage.page().url());
    }
}
