package ru.sbt.mipt.oop.home.alarm;

public interface AlarmReactor {
    void onAlarmActiveState();
    void onAlarmInactiveState();
    void onAlarmSosState();
}
