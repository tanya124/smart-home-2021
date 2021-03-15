package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.iterator.DoorInRoomIterator;
import ru.sbt.mipt.oop.home.iterator.SmartHomeSmartIterator;
import ru.sbt.mipt.oop.home.iterator.SmartIterator;

import java.util.Queue;

public class DoorEventHandler implements EventHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public DoorEventHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void doAction(SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED || event.getType() == SensorEventType.DOOR_OPEN) {
            SmartHomeSmartIterator smartHomeIterator = smartHome.createIterator();
            while (smartHomeIterator.hasMore()) {
                Room room = smartHomeIterator.getNext();
                DoorInRoomIterator doorsIterator = room.createDoorInRoomIterator();
                while (doorsIterator.hasMore()) {
                    Door door = doorsIterator.getNext();
                    if (event.getType() == SensorEventType.DOOR_CLOSED) {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    } else {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    }
                }
            }
        }
    }
}