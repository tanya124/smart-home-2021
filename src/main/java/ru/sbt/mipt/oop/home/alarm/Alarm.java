package ru.sbt.mipt.oop.home.alarm;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm() {
        state = new AlarmDeactivateState(this);
        code = "123";
    }

    public AlarmState getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void sos() {
        state.sos();
    }
}
