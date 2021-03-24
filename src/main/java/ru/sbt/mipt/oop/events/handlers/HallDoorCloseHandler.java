package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.*;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.CheckIsHallDoorAction;
import ru.sbt.mipt.oop.home.action.LightOffInAllHomeAction;

import java.util.Queue;

public class HallDoorCloseHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public HallDoorCloseHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void handleEvent(Event _event) {
        if (_event instanceof SensorEvent) {
            SensorEvent event = (SensorEvent) _event;
            if (event.getType() == EventType.DOOR_CLOSED && isHallDoor(event)) {
                LightOffInAllHomeAction action = new LightOffInAllHomeAction(sensorCommandQueue);
                smartHome.execute(action);
            }
        }
    }

    private boolean isHallDoor(SensorEvent event) {
        CheckIsHallDoorAction action = new CheckIsHallDoorAction(event.getObjectId());
        smartHome.execute(action);
        return action.isHallDoor();
    }
}
