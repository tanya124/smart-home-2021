package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.home.alarm.Alarm;


public class AlarmHandler implements EventHandler {
    private Alarm alarm;

    public AlarmHandler(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(Event event) {
        if (!isAlarmEvent(event)) return;

        AlarmEvent alarmEvent = (AlarmEvent) event;
        if (event.getType() == EventType.ALARM_ACTIVATE) {
            alarm.activate(alarmEvent.getCode());
        } else if (event.getType() == EventType.ALARM_DEACTIVATE) {
            alarm.deactivate(alarmEvent.getCode());
        }
    }

    private boolean isAlarmEvent(Event event) {
        return event instanceof AlarmEvent;
    }
}
