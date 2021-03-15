package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.iterator.LightInRoomIterator;
import ru.sbt.mipt.oop.home.iterator.SmartHomeSmartIterator;

public class LightOffCommandHandler implements CommandHandler {
    private SmartHome smartHome;

    public LightOffCommandHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doAction(SensorCommand command) {
        if (command.getType() == CommandType.LIGHT_OFF) {
            SmartHomeSmartIterator smartHomeIterator = smartHome.createIterator();
            while (smartHomeIterator.hasMore()) {
                Room room = smartHomeIterator.getNext();
                LightInRoomIterator lightIterator = room.createLightInRoomIterator();
                while (lightIterator.hasMore()) {
                    Light light = lightIterator.getNext();
                    if (light.getId().equals(command.getObjectId())) {
                        light.setOn(false);
                        System.out.println("Pretent we're sending command " + command);
                    }
                }
            }
        }
    }
}
