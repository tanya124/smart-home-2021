package ru.sbt.mipt.oop.home.action;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;

import java.util.Queue;

public class LightOffInAllHomeAction implements Action{
    private Queue<SensorCommand> sensorCommandQueue;

    public LightOffInAllHomeAction(Queue<SensorCommand> sensorCommandQueue) {
        this.sensorCommandQueue = sensorCommandQueue;
    }

    @Override
    public void execute(Actionable object) {
        if (object instanceof Light) {
            Light light = (Light) object;
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            sensorCommandQueue.add(command);
        }
    }
}
