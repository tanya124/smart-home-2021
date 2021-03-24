package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.*;

public class EventHandlerDecorator {
    EventHandler handler;
    SmartHome smartHome;

    public EventHandlerDecorator(EventHandler handler, SmartHome smartHome) {
        this.handler = handler;
        this.smartHome = smartHome;
    }

    public void handleEvent(Event event) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm.getState() instanceof AlarmDeactivateState) {
            handler.handleEvent(event);
        } else if (alarm.getState() instanceof AlarmActiveState) {
            if (event instanceof SensorEvent) {
                alarm.sos();
                System.out.println("SOS");
            } else {
                handler.handleEvent(event);
            }
        } else if (alarm.getState() instanceof AlarmSosState) {
            if (event instanceof SensorEvent) {
                System.out.println("SOS");
            } else {
                handler.handleEvent(event);
            }
        }
    }
}
