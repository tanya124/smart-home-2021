package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Door;

public class FindDoorAction implements Action {
    private String objectId;
    private Door result;

    public FindDoorAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Door) {
            Door door = (Door) object;
            if (door.getId().equals(objectId)) {
                result = door;
            }
        }
    }

    public Door getResult() {
        return result;
    }
}