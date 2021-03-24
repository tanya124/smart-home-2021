package ru.sbt.mipt.oop.home.alarm;

public class AlarmSosState implements AlarmState {
    private Alarm alarm;

    public AlarmSosState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new AlarmDeactivateState(alarm));
        }
    }

    @Override
    public void sos() {}
}
