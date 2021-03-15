package ru.sbt.mipt.oop.home.iterator;

import ru.sbt.mipt.oop.home.Room;

import java.util.Collection;
import java.util.Iterator;

public class SmartHomeSmartIterator implements SmartIterator {
    private Iterator<Room> iter;


    public SmartHomeSmartIterator(Collection<Room> rooms) {
        iter = rooms.iterator();
    }

    @Override
    public Room getNext() {
        Room next = null;
        if (iter.hasNext()) {
            next = iter.next();
        }
        return next;
    }

    @Override
    public boolean hasMore() {
        return iter.hasNext();
    }
}
