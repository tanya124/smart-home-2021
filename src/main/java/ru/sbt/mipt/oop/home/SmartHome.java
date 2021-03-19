package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.home.action.*;
import ru.sbt.mipt.oop.home.iterator.*;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, IterableCollection {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        SmartIterator iterator = createIterator();
        while (iterator.hasMore()) {
            Room room = iterator.getNext();
            room.execute(action);
        }
    }


    @Override
    public SmartHomeSmartIterator createIterator() {
        return new SmartHomeSmartIterator(getRooms());
    }
}
