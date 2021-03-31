package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.home.alarm.*;

public class EventHandlerDecorator implements EventHandler, AlarmReactor{
    private EventHandler handler;
    private Alarm alarm;
    private Event event;

    public EventHandlerDecorator(EventHandler handler, Alarm alarm) {
        this.handler = handler;
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(Event event) {
        this.event = event;
        alarm.react(this);
    }

    @Override
    public void onAlarmActiveState() {
        if (event instanceof SensorEvent) {
            alarm.sos();
            System.out.println("SOS");
        } else {
            handler.handleEvent(event);
        }
    }

    @Override
    public void onAlarmInactiveState() {
        handler.handleEvent(event);
    }

    @Override
    public void onAlarmSosState() {
        if (event instanceof SensorEvent) {
            System.out.println("SOS");
        } else {
            handler.handleEvent(event);
        }
    }
}
