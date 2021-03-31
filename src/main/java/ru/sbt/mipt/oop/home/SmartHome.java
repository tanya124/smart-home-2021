package ru.sbt.mipt.oop.home;

import ru.sbt.mipt.oop.home.action.*;
import ru.sbt.mipt.oop.home.alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm("123");
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        alarm = new Alarm("123");
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
