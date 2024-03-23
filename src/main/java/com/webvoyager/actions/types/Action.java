package com.webvoyager.actions.types;

import com.webvoyager.page.MarkedPage;

public sealed interface Action permits Answer, Click, GoBack, Google, Scroll, Type, Wait {

    String applyTo(MarkedPage markedPage);
}
