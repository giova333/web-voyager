package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

public final class Google implements Action {

    private static final String GOOGLE_URL = "https://google.com";

    @Override
    public String applyTo(MarkedPage markedPage) {
        markedPage.navigateTo(GOOGLE_URL);
        return String.format("Navigated to %s", GOOGLE_URL);
    }
}
