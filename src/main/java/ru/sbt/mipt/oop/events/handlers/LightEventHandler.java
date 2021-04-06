package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.*;


public class LightEventHandler implements EventHandler {
    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
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