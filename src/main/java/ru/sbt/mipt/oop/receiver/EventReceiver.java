package ru.sbt.mipt.oop.receiver;

import ru.sbt.mipt.oop.events.Event;

public interface EventReceiver {
    Event getNextEvent();
}
