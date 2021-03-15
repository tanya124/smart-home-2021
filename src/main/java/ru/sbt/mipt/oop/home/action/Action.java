package ru.sbt.mipt.oop.home.action;

public class Action {
    private final ActionType actionType;

    public Action(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
