package com.webvoyager.page;

import com.microsoft.playwright.Page;
import com.webvoyager.actions.types.Scroll;

import java.util.Base64;
import java.util.List;

import static java.lang.String.format;

public record MarkedPage(
        List<WebElement> elements,
        Page page) {

    public String asBase64() {
        var screenshot = page.screenshot();
        return new String(Base64.getEncoder().encode(screenshot));
    }

    public void click(int webElementIndex) {
        var element = elements.get(webElementIndex);
        page.mouse().click(element.x(), element.y());
    }

    public void typeAndSubmit(int webElementIndex, String text) {
        click(webElementIndex);
        page.keyboard().type(text);
        page.keyboard().press("Enter");
    }

    public void scroll(Integer webElementIndex, Scroll.ScrollDirection direction, Scroll.ScrollOption scrollOption) {
        if (scrollOption == Scroll.ScrollOption.WINDOW) {
            int scrollAmount = direction == Scroll.ScrollDirection.UP ? -500 : 500;
            page.evaluate("window.scrollBy(0, " + scrollAmount + ")");
        } else {
            var element = elements.get(webElementIndex);
            int scrollAmount = direction == Scroll.ScrollDirection.UP ? -500 : 500;
            page.mouse().move(element.x(), element.y());
            page.mouse().wheel(0, scrollAmount);
        }
    }

    public void goBack() {
        page.goBack();
    }

    public String sleep(int seconds) {
        try {
            Thread.sleep(5 * 1000);
            return format("Waited for %d seconds", seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }
}
