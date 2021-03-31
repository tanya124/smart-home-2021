package ru.sbt.mipt.oop.home.alarm;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm(String code) {
        state = new AlarmInactiveState(this);
        this.code = code;
    }

    public void activate(String code) {
        state = state.activate(code);
    }

    public void deactivate(String code) {
        state = state.deactivate(code);
    }

    public void sos() {
        state = state.sos();
    }

    public void react(AlarmReactor alarmReactor) {
        state.react(alarmReactor);
    }

    public boolean isCorrectCode(String code) {
        return code.equals(this.code);
    }

    public boolean alarmIsActivate() {
        return state instanceof AlarmActiveState;
    }

    public boolean alarmIsInactivate() {
        return state instanceof AlarmInactiveState;
    }

    public boolean alarmIsSos() {
        return state instanceof AlarmSosState;
    }
}
