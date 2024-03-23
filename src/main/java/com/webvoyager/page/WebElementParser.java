package com.webvoyager.page;

import java.util.List;
import java.util.Map;

class WebElementParser {

    List<WebElement> parseEvaluationResult(List<Map<String, Object>> evaluation) {
        return evaluation.stream()
                .map(this::mapToWebElement)
                .toList();
    }

    private WebElement mapToWebElement(Map<String, Object> stringObjectMap) {
        return new WebElement(
                ((Number) stringObjectMap.get("x")).doubleValue(),
                ((Number) stringObjectMap.get("y")).doubleValue(),
                (String) stringObjectMap.get("type"),
                (String) stringObjectMap.get("text"),
                (String) stringObjectMap.get("ariaLabel")
        );
    }
}