package com.webvoyager;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class WebVoyager {

    private static final int DEFAULT_MAX_DEPTH = 10;
    private static final String DEFAULT_INITIAL_URL = "https://google.com";

    private final TaskExecutor taskExecutor;
    private final String initialUrl;

    public WebVoyager(int maxDepth, String initialUrl) {
        this.taskExecutor = new TaskExecutor(maxDepth);
        this.initialUrl = initialUrl;
    }

    public WebVoyager() {
        this(DEFAULT_MAX_DEPTH, DEFAULT_INITIAL_URL);
    }

    public String execute(String task) {
        try (var playwright = Playwright.create();
             var browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false))) {

            var page = browser.newPage();
            page.navigate(initialUrl);

            return taskExecutor.executeTaskOnPage(task, page);
        }
    }

}
