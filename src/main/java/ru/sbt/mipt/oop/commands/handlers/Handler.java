package ru.sbt.mipt.oop.commands.handlers;

import ru.sbt.mipt.oop.commands.SensorCommand;

public interface Handler {
    public void doAction(SensorCommand command);
}
