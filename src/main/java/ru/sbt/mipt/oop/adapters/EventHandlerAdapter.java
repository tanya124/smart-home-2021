package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;

public class EventHandlerAdapter implements EventHandler {
    private ru.sbt.mipt.oop.events.handlers.EventHandler handler;

    public EventHandlerAdapter(ru.sbt.mipt.oop.events.handlers.EventHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = null;
        switch (event.getEventType()) {
            case "LightIsOn":
                sensorEvent = new SensorEvent(EventType.LIGHT_ON, event.getObjectId());
                break;
            case "LightIsOff":
                sensorEvent = new SensorEvent(EventType.LIGHT_OFF, event.getObjectId());
                break;
            case "DoorIsOpen":
                sensorEvent = new SensorEvent(EventType.DOOR_OPEN, event.getObjectId());;
                break;
            case "DoorIsClosed":
                sensorEvent = new SensorEvent(EventType.DOOR_CLOSED, event.getObjectId());
                break;
            default:
                break;
        }

        // события типа DoorIsLocked и DoorIsUnlocked игнорируем
        if (sensorEvent != null) {
            handler.handleEvent(sensorEvent);
        }
    }
}
