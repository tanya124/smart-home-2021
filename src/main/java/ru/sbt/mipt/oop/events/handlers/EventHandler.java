package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface EventHandler {
    void handleEvent(SensorEvent event);
}
