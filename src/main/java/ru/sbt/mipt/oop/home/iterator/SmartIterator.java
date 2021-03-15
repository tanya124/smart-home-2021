package ru.sbt.mipt.oop.home.iterator;

public interface SmartIterator {
    <T> T getNext();

    boolean hasMore();
}
