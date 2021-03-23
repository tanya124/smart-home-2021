package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Light;

public class FindLightAction implements Action {
    private String objectId;
    private Light result;

    public FindLightAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Light) {
            Light light = (Light) object;
            if (light.getId().equals(objectId)) {
                result = light;
            }
        }
    }

    public Light getResult() {
        return result;
    }
}