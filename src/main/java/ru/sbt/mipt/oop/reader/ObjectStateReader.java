package ru.sbt.mipt.oop.reader;

interface ObjectStateReader {
    <T> T readObject(Class<T> clazz);
}
