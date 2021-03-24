package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Room;

public class CheckIsHallDoorAction implements Action {
    private String objectId;
    private boolean isHallDoor;
    private String currentRoomName;

    public CheckIsHallDoorAction(String objectId) {
        this.objectId = objectId;
        this.isHallDoor = false;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Room) {
            Room room = (Room) object;
            this.currentRoomName = room.getName();
        }
        if (object instanceof Door) {
            Door door = (Door) object;
            if (door.getId().equals(objectId) && currentRoomName.equals("hall")) {
                isHallDoor = true;
            }
        }
    }

    public boolean isHallDoor() {
        return isHallDoor;
    }
}