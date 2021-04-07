package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.alarm.Alarm;

public class ActivateAlarmCommand implements Command {
    private Alarm alarm;
    private String code;

    public ActivateAlarmCommand(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }
    @Override
    public void execute() {
        alarm.activate(code);
    }
}
