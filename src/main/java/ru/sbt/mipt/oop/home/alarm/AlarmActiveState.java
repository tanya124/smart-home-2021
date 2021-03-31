package ru.sbt.mipt.oop.home.alarm;

public class AlarmActiveState implements AlarmState {
    private Alarm alarm;

    public AlarmActiveState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public AlarmState activate(String code) { return this;}

    @Override
    public AlarmState deactivate(String code) {
        if (alarm.isCorrectCode(code)) {
            return new AlarmInactiveState(alarm);
        } else {
            return new AlarmSosState(alarm);
        }
    }

    @Override
    public AlarmState sos() {
        return new AlarmSosState(alarm);
    }

    @Override
    public void react(AlarmReactor alarmReactor) {
        alarmReactor.onAlarmActiveState();
    }
}
