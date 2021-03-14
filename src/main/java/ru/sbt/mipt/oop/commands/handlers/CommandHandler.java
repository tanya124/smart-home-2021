package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;

public interface CommandHandler {
    public void doAction(SensorCommand command);
}
