package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.CloseHallDoorAction;

public class CloseHallDoorCommand implements Command {
    private SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        CloseHallDoorAction action = new CloseHallDoorAction(smartHome);
        action.execute(smartHome);
    }
}
