package com.webvoyager.page;

public record WebElement(
        double x,
        double y,
        String type,
        String text,
        String ariaLabel) {
}
