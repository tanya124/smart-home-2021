package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Queue;

public class LightOnHandler implements Handler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public LightOnHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void doAction(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");

                }
            }
        }
    }
}
