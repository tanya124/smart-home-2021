package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.iterator.*;

import java.util.Queue;

public class HallDoorCloseHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public HallDoorCloseHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void doAction(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED && isHallDoor(event)) {
            SmartHomeSmartIterator smartHomeSmartIterator = smartHome.createIterator();
            while (smartHomeSmartIterator.hasMore()) {
                Room room = smartHomeSmartIterator.getNext();
                LightInRoomIterator lightIterator = room.createLightInRoomIterator();
                while (lightIterator.hasMore()) {
                    Light light = lightIterator.getNext();
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    sensorCommandQueue.add(command);
                }

            }
        }
    }

    private boolean isHallDoor(SensorEvent event) {
        SmartHomeSmartIterator smartHomeSmartIterator = smartHome.createIterator();
        while (smartHomeSmartIterator.hasMore()) {
            Room room = smartHomeSmartIterator.getNext();
            DoorInRoomIterator doorIterator = room.createDoorInRoomIterator();
            while (doorIterator.hasMore()) {
                Door door = doorIterator.getNext();
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
