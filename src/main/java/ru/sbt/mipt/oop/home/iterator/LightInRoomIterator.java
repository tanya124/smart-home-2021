package ru.sbt.mipt.oop.home.iterator;

import ru.sbt.mipt.oop.home.Light;

import java.util.Collection;
import java.util.Iterator;

public class LightInRoomIterator implements SmartIterator {
    Iterator<Light> iter;

    public LightInRoomIterator(Collection<Light> lights) {
        iter = lights.iterator();
    }

    @Override
    public Light getNext() {
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