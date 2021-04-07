package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.LightOffInHallRoomAction;

public class TurnOnLightInHallCommand implements Command {
    private SmartHome smartHome;

    public TurnOnLightInHallCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightOffInHallRoomAction action = new LightOffInHallRoomAction();
        action.execute(smartHome);
    }
}
