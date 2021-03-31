package ru.sbt.mipt.oop.home.alarm;

public interface AlarmState {

    AlarmState activate(String code);
    AlarmState deactivate(String code);
    AlarmState sos();
    void react(AlarmReactor alarmReactor);
}
