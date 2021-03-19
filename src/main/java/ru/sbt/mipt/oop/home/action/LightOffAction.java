package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Light;

public class LightOffAction implements Action {
    private String objectId;

    public LightOffAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Light) {
            Light light = (Light) object;
            if (light.getId().equals(objectId)) {
                light.setOn(false);
            }
        }
    }
}