package ru.sbt.mipt.oop.home.alarm;

public class AlarmInactiveState implements AlarmState {
    private Alarm alarm;

    public AlarmInactiveState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public AlarmState activate(String code) {
        if (alarm.isCorrectCode(code)) {
            return new AlarmActiveState(alarm);
        }
        return this;
    }

    @Override
    public AlarmState deactivate(String code) { return this; }

    @Override
    public AlarmState sos() {
        return new AlarmSosState(alarm);
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmInactiveState();
    }
}
