package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.CheckIsHallDoorAction;
import ru.sbt.mipt.oop.home.action.LightOffInAllHomeAction;

import java.util.Queue;
import java.util.zip.CheckedInputStream;

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
        CheckIsHallDoorAction action = new CheckIsHallDoorAction(event.getObjectId());
        smartHome.execute(action);
        return action.isHallDoor();
    }
}
