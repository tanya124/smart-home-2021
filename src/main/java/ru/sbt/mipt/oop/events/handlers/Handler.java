package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface Handler {
    public void doAction(SensorEvent event);
}
