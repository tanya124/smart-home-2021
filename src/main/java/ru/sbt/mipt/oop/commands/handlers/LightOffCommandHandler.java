package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.*;
import ru.sbt.mipt.oop.home.iterator.LightInRoomIterator;
import ru.sbt.mipt.oop.home.iterator.SmartHomeSmartIterator;

public class LightOffCommandHandler implements CommandHandler {
    private SmartHome smartHome;

    public LightOffCommandHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void doAction(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
        Action action = new Action(ActionType.LIGHT_OFF);
        smartHome.execute(action);
    }
}
