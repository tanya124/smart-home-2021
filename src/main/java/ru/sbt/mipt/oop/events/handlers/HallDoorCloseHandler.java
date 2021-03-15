package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.iterator.DoorInRoomIterator;
import ru.sbt.mipt.oop.home.iterator.LightInRoomIterator;
import ru.sbt.mipt.oop.home.iterator.SmartHomeSmartIterator;

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
