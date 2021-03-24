package ru.sbt.mipt.oop.receiver;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.Queue;

public class QueueEventReceiver implements EventReceiver {
    private Queue<Event> events;

    public QueueEventReceiver(Queue<Event> events) {
        this.events = events;
    }

    @Override
    public Event getNextEvent() {
        if (!events.isEmpty()) {
            return events.poll();
        }
        return null;
    }
}
