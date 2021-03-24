package ru.sbt.mipt.oop.home.alarm;

public interface AlarmState {

    void activate(String code);
    void deactivate(String code);
    void sos();
}
