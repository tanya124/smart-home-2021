package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Queue;

public class LightEventHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public LightEventHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }
    @Override
    public void doAction(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_OFF || event.getType() == SensorEventType.LIGHT_ON) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == SensorEventType.LIGHT_OFF) {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                        } else {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                        }
                    }
                }
            }
        }
    }
}