package ru.sbt.mipt.oop.reader;

import java.io.IOException;

interface ObjectStateReaderFromFile {
    Object readObjectState(String path, Class clazz) throws IOException;
}
