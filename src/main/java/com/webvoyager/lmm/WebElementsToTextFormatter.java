package com.webvoyager.lmm;

import com.webvoyager.page.WebElement;

import java.util.List;

class WebElementsToTextFormatter {

    String formatElements(List<WebElement> webElements) {
        var stringBuilder = new StringBuilder();
        for (int i = 0; i < webElements.size(); i++) {
            var webElement = webElements.get(i);
            var description = webElement.ariaLabel().isEmpty() ? webElement.text() : webElement.ariaLabel();
            stringBuilder
                    .append(i)
                    .append(" ")
                    .append("(<").append(webElement.type()).append("/>):")
                    .append("\"").append(description).append("\"")
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
