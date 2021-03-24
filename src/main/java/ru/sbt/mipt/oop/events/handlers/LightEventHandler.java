package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.*;

import java.util.Queue;

public class LightEventHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public LightEventHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void handleEvent(Event _event) {
        if (_event instanceof SensorEvent) {
            SensorEvent event = (SensorEvent) _event;
            if (event.getType() == EventType.LIGHT_OFF) {
                LightOffAction action = new LightOffAction(event.getObjectId());
                smartHome.execute(action);
                System.out.println("Light " + event.getObjectId() + " was turned off.");
            } else if (event.getType() == EventType.LIGHT_ON) {
                LightOnAction action = new LightOnAction(event.getObjectId());
                smartHome.execute(action);
                System.out.println("Light " + event.getObjectId() + " was turned on.");
            }
        }
    }
}