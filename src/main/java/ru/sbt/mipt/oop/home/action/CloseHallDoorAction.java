package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.SmartHome;

public class CloseHallDoorAction implements Action {
    private SmartHome smartHome;

    public CloseHallDoorAction(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Door) {
            Door door = (Door) object;
            if (isHallDoor(door)) {
                door.setOpen(false);
            }
        }
    }

    private boolean isHallDoor(Door door) {
        CheckIsHallDoorAction action = new CheckIsHallDoorAction(door.getId());
        smartHome.execute(action);
        return action.isHallDoor();
    }
}
