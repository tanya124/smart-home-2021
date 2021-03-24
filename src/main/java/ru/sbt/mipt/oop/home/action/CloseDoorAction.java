package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Door;

public class CloseDoorAction implements Action {
    private String objectId;

    public CloseDoorAction(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Door) {
            Door door = (Door) object;
            if (door.getId().equals(objectId)) {
                door.setOpen(false);
            }
        }
    }
}
