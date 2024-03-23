package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

import static java.lang.String.format;

public final class Wait implements Action {

    public static final int SECONDS = 5;

    @Override
    public String applyTo(MarkedPage markedPage) {
        markedPage.sleep(SECONDS);
        return format("Waited for %d seconds", SECONDS);
    }
}
