package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.HashMap;

public class EventHandlerAdapter implements EventHandler {
    private ru.sbt.mipt.oop.events.handlers.EventHandler handler;
    private HashMap<String, EventType> mapOfEventType;

    public EventHandlerAdapter(
            ru.sbt.mipt.oop.events.handlers.EventHandler handler,
            HashMap<String, EventType> mapOfEventType) {
        this.handler = handler;
        this.mapOfEventType = mapOfEventType;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = null;
        EventType eventType = mapOfEventType.get(event.getEventType());
        if (eventType != null) {
            sensorEvent = new SensorEvent(eventType, event.getObjectId());
        }

        // события типа DoorIsLocked и DoorIsUnlocked игнорируем
        if (sensorEvent != null) {
            handler.handleEvent(sensorEvent);
        }
    }
}
