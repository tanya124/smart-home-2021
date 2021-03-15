package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.home.action.Action;
import ru.sbt.mipt.oop.home.action.ActionType;
import ru.sbt.mipt.oop.home.action.Actionable;

public class Light implements Actionable, Device {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        if (action.getActionType() == ActionType.LIGHT_ON) {
            setOn(true);
        } else if (action.getActionType() == ActionType.LIGHT_OFF) {
            setOn(false);
        }
    }
}
