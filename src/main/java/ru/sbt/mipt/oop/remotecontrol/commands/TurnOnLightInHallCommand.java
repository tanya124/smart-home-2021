package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.LightOnInHallRoomAction;

public class TurnOnLightInHallCommand implements Command {
    private SmartHome smartHome;

    public TurnOnLightInHallCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightOnInHallRoomAction action = new LightOnInHallRoomAction();
        smartHome.execute(action);
    }
}
