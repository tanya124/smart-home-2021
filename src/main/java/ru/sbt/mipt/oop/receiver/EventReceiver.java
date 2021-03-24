package ru.sbt.mipt.oop.receiver;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface EventReceiver {
    public SensorEvent getNextSensorEvent();
}
