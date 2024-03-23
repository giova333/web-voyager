package com.webvoyager.actions;

import java.util.List;

public record ActionHistory(
        List<String> actions) {

    public void addAction(String action) {
        actions.add(action);
    }

    public String toString() {
        var stringBuilder = new StringBuilder();
        for (int i = 0; i < actions.size(); i++) {
            stringBuilder
                    .append(i + 1)
                    .append(".")
                    .append(actions.get(i))
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
