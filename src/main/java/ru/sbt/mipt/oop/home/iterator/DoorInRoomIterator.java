package ru.sbt.mipt.oop.home.iterator;

import ru.sbt.mipt.oop.home.Door;

import java.util.Collection;
import java.util.Iterator;

public class DoorInRoomIterator implements SmartIterator {
    Iterator<Door> iter;

    public DoorInRoomIterator(Collection<Door> doors) {
        iter = doors.iterator();
    }

    @Override
    public Door getNext() {
        if (iter.hasNext()) {
            return iter.next();
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        return iter.hasNext();
    }
}
