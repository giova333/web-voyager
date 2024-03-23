package com.webvoyager.actions;

import com.webvoyager.actions.types.Action;
import com.webvoyager.actions.types.Answer;
import com.webvoyager.actions.types.Click;
import com.webvoyager.actions.types.GoBack;
import com.webvoyager.actions.types.Google;
import com.webvoyager.actions.types.Scroll;
import com.webvoyager.actions.types.Type;
import com.webvoyager.actions.types.Wait;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ActionFactory {

    private final Map<String, Function<String[], Action>> actionMap = new HashMap<>();

    public ActionFactory() {
        actionMap.put("Click", Click::new);
        actionMap.put("Type", Type::new);
        actionMap.put("Scroll", Scroll::new);
        actionMap.put("Wait", data -> new Wait());
        actionMap.put("GoBack", data -> new GoBack());
        actionMap.put("Google", data -> new Google());
        actionMap.put("Answer", Answer::new);
    }

    public Action createAction(String actionType, String[] actionData) {
        var actionConstructor = actionMap.get(actionType);
        if (actionConstructor == null) {
            throw new IllegalStateException("Unexpected value: " + actionType);
        }
        return actionConstructor.apply(actionData);
    }
}
