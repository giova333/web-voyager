package com.webvoyager.page;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Map;

import static com.webvoyager.utils.ResourceLoader.loadResource;

public class PageMarker {

    public static final String SCRIPT_PATH = "mark-page.js";

    private final WebElementParser webElementParser = new WebElementParser();

    public MarkedPage markPage(Page page) {
        var markPageScript = loadResource(SCRIPT_PATH);

        page.evaluate(markPageScript);
        var markedPage = page.evaluate("markPage()");
        var webElements = webElementParser.parseEvaluationResult((List<Map<String, Object>>) markedPage);

        return new MarkedPage(webElements, page);
    }

    public void unmarkPage(MarkedPage markedPage) {
        markedPage.page().evaluate("unmarkPage()");
    }
}
