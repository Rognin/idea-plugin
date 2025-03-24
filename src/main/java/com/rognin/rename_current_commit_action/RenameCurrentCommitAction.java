package com.rognin.rename_current_commit_action;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

public class RenameCurrentCommitAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent event) {
        // Using the event, evaluate the context,
        // and enable or disable the action.
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        String newCommitMessage = Messages.showInputDialog(project, "Enter new commit message:", "Set Commit Message", Messages.getQuestionIcon());
        if (newCommitMessage == null || newCommitMessage.trim().equals("Enter a commit message")) return;

//        System.out.println("New commit message : " + newCommitMessage);

        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow commitWindow = toolWindowManager.getToolWindow("Commit");
        if (commitWindow != null) {
            commitWindow.activate(() -> {
                DataManager.getInstance().getDataContextFromFocusAsync().onSuccess(dataContext -> {
                    CommitMessageI commitMessageComponent = VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(dataContext);
                    if (commitMessageComponent != null) {
                        commitMessageComponent.setCommitMessage(newCommitMessage);
                    } else {
                        Messages.showErrorDialog(project, "Could not find commit message field!", "Error");
                    }
                });
            });
        }
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;  // Ensures the action runs on the EDT
    }

}