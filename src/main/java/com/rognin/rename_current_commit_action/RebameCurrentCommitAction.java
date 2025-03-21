package com.rognin.rename_current_commit_action;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class RebameCurrentCommitAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent event) {
        // Using the event, evaluate the context,
        // and enable or disable the action.
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

    }

//    @Override
//    public @NotNull ActionUpdateThread getActionUpdateThread(){
//        return super.getActionUpdateThread();
//    }

}
