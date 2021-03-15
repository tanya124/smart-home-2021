package ru.sbt.mipt.oop.home.iterator;

import ru.sbt.mipt.oop.home.Device;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;

import java.util.Collection;
import java.util.Iterator;

public class RoomSmartIterator implements SmartIterator {
    private Iterator<Light> lightIter;
    private Iterator<Door> doorIter;

    public RoomSmartIterator(Collection<Door> doors, Collection<Light> lights) {
        lightIter = lights.iterator();
        doorIter = doors.iterator();
    }

    @Override
    public Device getNext() {
        if (lightIter.hasNext()) {
            return lightIter.next();
        } else if (doorIter.hasNext()) {
            return doorIter.next();
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        return lightIter.hasNext() || doorIter.hasNext();
    }
}
