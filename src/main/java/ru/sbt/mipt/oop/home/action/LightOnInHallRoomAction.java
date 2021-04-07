package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;

public class LightOnInHallRoomAction implements Action {
    private String currentRoomName;

    @Override
    public void execute(Actionable object) {
        if (object instanceof Room) {
            Room room = (Room) object;
            this.currentRoomName = room.getName();
        }

        if (object instanceof Light) {
            Light light = (Light) object;
            if (currentRoomName.equals("hall")) {
                light.setOn(true);
            }
        }
    }
}
