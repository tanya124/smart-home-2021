package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Queue;

public class DoorCloseEventHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public DoorCloseEventHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void doAction(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
            }
        }
    }

}