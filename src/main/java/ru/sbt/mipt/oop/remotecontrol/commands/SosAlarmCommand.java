package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.alarm.Alarm;

public class SosAlarmCommand implements Command {
    private Alarm alarm;

    public SosAlarmCommand(ru.sbt.mipt.oop.home.alarm.Alarm alarm) {
        this.alarm = alarm;
    }
    @Override
    public void execute() {
        alarm.sos();
    }
}
