package ru.sbt.mipt.oop.home.alarm;

public class AlarmActiveState implements AlarmState {
    private Alarm alarm;

    public AlarmActiveState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setState(new AlarmDeactivateState(alarm));
        } else {
            sos();
        }
    }

    @Override
    public void sos() {
        alarm.setState(new AlarmSosState(alarm));
    }
}
