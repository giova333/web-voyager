package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

import java.util.Arrays;

import static java.lang.String.format;

public final class Scroll implements Action {

    private int webElementIndex;
    private ScrollDirection direction;
    private ScrollOption option;

    public enum ScrollDirection {
        UP("up"),
        DOWN("down");

        private final String type;

        ScrollDirection(String type) {
            this.type = type;
        }

        public static ScrollDirection from(String type) {
            return Arrays.stream(ScrollDirection.values())
                    .filter(scrollDirection -> scrollDirection.type.equals(type))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid scroll direction"));
        }
    }

    public enum ScrollOption {
        WINDOW,
        ELEMENT
    }


    public Scroll(String[] actionData) {
        if (actionData.length != 2) {
            throw new IllegalArgumentException("Scroll action should have 2 parameters");
        }
        var type = (String) actionData[0];
        this.direction = ScrollDirection.from(actionData[1]);
        if (type.equals("window")) {
            this.option = ScrollOption.WINDOW;
        } else {
            this.webElementIndex = Integer.parseInt(type);
            this.option = ScrollOption.ELEMENT;
        }
    }

    @Override
    public String applyTo(MarkedPage markedPage) {
        markedPage.scroll(webElementIndex, direction, option);
        return format("Scroll %s in element", direction.toString().toLowerCase());
    }
}
