package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;


public class LightOffCommandHandler implements CommandHandler {
    @Override
    public void doAction(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
