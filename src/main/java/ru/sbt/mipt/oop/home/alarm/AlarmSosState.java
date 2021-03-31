package ru.sbt.mipt.oop.home.alarm;

public class AlarmSosState implements AlarmState {
    private Alarm alarm;

    public AlarmSosState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public AlarmState activate(String code) { return this; }

    @Override
    public AlarmState deactivate(String code) {
        if (alarm.isCorrectCode(code)) {
            return new AlarmInactiveState(alarm);
        }
        return this;
    }

    @Override
    public AlarmState sos() { return this; }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmSosState();
    }
}
