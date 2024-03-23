package com.webvoyager.lmm;

import com.webvoyager.actions.ActionFactory;
import com.webvoyager.actions.types.Action;

import java.util.Arrays;

public class ActionParser {

    private final ActionFactory actionFactory = new ActionFactory();

    public Action parseAction(String action) {
        var lines = action.split("\n");
        var actionLine = lines[lines.length - 1];
        if (!actionLine.startsWith("Action:")) {
            throw new IllegalArgumentException("Action line should start with 'Action:'");
        }
        var actionData = actionLine.replace("Action:", "").trim();

        var actionParts = actionData.split(":");
        var actionType = actionParts[0];
        var actionDataParts = Arrays.copyOfRange(actionParts, 1, actionParts.length);

        return actionFactory.createAction(actionType, actionDataParts);
    }
}
