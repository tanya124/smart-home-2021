package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.*;
import ru.sbt.mipt.oop.home.iterator.*;


import java.util.Queue;

public class DoorEventHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public DoorEventHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_OPEN) {
            OpenDoorAction action = new OpenDoorAction(event.getObjectId());
            smartHome.execute(action);
            System.out.println("Door " + event.getObjectId() + " was opened.");
        } else if (event.getType() == SensorEventType.DOOR_CLOSED) {
            CloseDoorAction action = new CloseDoorAction(event.getObjectId());
            smartHome.execute(action);
            System.out.println("Door " + event.getObjectId() + " was closed.");
        }
    }
}