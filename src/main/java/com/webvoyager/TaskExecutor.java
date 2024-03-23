package com.webvoyager;

import com.microsoft.playwright.Page;
import com.webvoyager.actions.ActionHistory;
import com.webvoyager.actions.types.Answer;
import com.webvoyager.lmm.LMMService;
import com.webvoyager.page.PageMarker;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class TaskExecutor {

    private final PageMarker pageMarker = new PageMarker();
    private final LMMService lmmService = new LMMService();
    private final int maxDepth;

    public TaskExecutor(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @SneakyThrows
    public String executeTaskOnPage(String task, Page page) {
        var actionHistory = new ActionHistory(new ArrayList<>());

        int currentDepth = 0;
        while (currentDepth < maxDepth) {
            var markedPage = pageMarker.markPage(page);

            try {
                var action = lmmService.askForAction(task, markedPage, actionHistory);
                pageMarker.unmarkPage(markedPage);

                var actionResult = action.applyTo(markedPage);
                actionHistory.addAction(actionResult);

                if (action instanceof Answer) {
                    return actionResult;
                }
            } catch (Exception e) {
                log.warn("An error occurred while executing the task", e);
            }
            currentDepth++;
            Thread.sleep(2000);
        }
        return "No answer found";
    }
}