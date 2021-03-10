package ru.sbt.mipt.oop.reader;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONObjectStateReaderFromFile implements ObjectStateReaderFromFile{

    @Override
    public Object readObjectState(String path, Class clazz) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(path)));
        return gson.fromJson(json, clazz);
    }
}
