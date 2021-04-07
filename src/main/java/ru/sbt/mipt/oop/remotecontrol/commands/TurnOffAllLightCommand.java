package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.LightOffInAllHomeAction;

import java.util.LinkedList;

public class TurnOffAllLightCommand implements Command {
    private SmartHome smartHome;

    public TurnOffAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void execute() {
        LightOffInAllHomeAction action = new LightOffInAllHomeAction(new LinkedList<>());
        action.execute(smartHome);
    }
}
