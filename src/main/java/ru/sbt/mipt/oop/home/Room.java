package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.home.action.*;
import ru.sbt.mipt.oop.home.iterator.*;

import java.util.Collection;

public class Room implements Actionable, IterableCollection {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        SmartIterator iterator = createIterator();
        while (iterator.hasMore()) {
            Device device = iterator.getNext();
            device.execute(action);
        }
    }

    @Override
    public SmartIterator createIterator() {
        return new RoomSmartIterator(doors, lights);
    }

    public DoorInRoomIterator createDoorInRoomIterator() {
        return new DoorInRoomIterator(doors);
    }

    public LightInRoomIterator createLightInRoomIterator() {
        return new LightInRoomIterator(lights);
    }
}
