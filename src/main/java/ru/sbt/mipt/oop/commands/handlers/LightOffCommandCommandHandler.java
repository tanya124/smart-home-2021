package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;

public class LightOffCommandCommandHandler implements CommandHandler {
    private SmartHome smartHome;

    public LightOffCommandCommandHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doAction(SensorCommand command) {
        if (command.getType() == CommandType.LIGHT_OFF) {
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
}
