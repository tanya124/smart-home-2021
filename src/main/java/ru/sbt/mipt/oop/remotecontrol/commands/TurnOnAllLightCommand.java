package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.LightOnAllHomeAction;

public class TurnOnAllLightCommand implements Command {
    private SmartHome smartHome;

    public TurnOnAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightOnAllHomeAction action = new LightOnAllHomeAction();
        smartHome.execute(action);
    }
}
