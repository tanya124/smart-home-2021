package ru.sbt.mipt.oop.home.alarm;

public class AlarmDeactivateState implements AlarmState {
    private Alarm alarm;

    public AlarmDeactivateState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new AlarmActiveState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void sos() {}
}
