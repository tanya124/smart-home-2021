package ru.sbt.mipt.oop.reader;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONObjectStateReader implements ObjectStateReader{
    private final String filepath;

    public JSONObjectStateReader(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public<T> T readObject(Class<T> clazz){
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(filepath)));
            return gson.fromJson(json, clazz);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
