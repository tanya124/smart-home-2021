package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.AlarmActiveState;
import ru.sbt.mipt.oop.home.alarm.AlarmDeactivateState;

public class AlarmHandler implements EventHandler {
    private SmartHome smartHome;

    public AlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event _event) {
        if (_event instanceof AlarmEvent) {
            AlarmEvent event = (AlarmEvent) _event;
            if (event.getType() == EventType.ALARM_ACTIVATE) {
                Alarm alarm = smartHome.getAlarm();
                alarm.activate(event.getCode());
                if (alarm.getState() instanceof AlarmActiveState) {
                    System.out.println("Alarm is activated.");
                } else {
                    System.out.println("Incorrect code!");
                }
            } else if (event.getType() == EventType.ALARM_DEACTIVATE) {
                Alarm alarm = smartHome.getAlarm();
                alarm.deactivate(event.getCode());
                if (alarm.getState() instanceof AlarmDeactivateState) {
                    System.out.println("Alarm is deactivated.");
                } else {
                    System.out.println("Incorrect code!");
                }
            }
        }
    }
}
