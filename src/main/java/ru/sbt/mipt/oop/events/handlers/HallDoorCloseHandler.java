package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.LightOffInAllHomeAction;
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
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED && isHallDoor(event)) {
            LightOffInAllHomeAction action = new LightOffInAllHomeAction(sensorCommandQueue);
            smartHome.execute(action);
        }
    }

    private boolean isHallDoor(SensorEvent event) {
        SmartHomeSmartIterator smartHomeSmartIterator = smartHome.createIterator();
        while (smartHomeSmartIterator.hasMore()) {
            Room room = smartHomeSmartIterator.getNext();
            SmartIterator iterator = room.createIterator();
            while (iterator.hasMore()) {
                Device device = iterator.getNext();
                if (device instanceof Door) {
                    Door door = (Door) device;
                    if (door.getId().equals(event.getObjectId())) {
                        if (room.getName().equals("hall")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
