package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;

public class LightOffCommandHandler implements Handler {
    private SmartHome smartHome;

    public LightOffCommandHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doAction(SensorCommand command) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(command.getObjectId())) {
                    light.setOn(false);
                    System.out.println("Pretent we're sending command " + command);
                }
            }
        }
    }
}
