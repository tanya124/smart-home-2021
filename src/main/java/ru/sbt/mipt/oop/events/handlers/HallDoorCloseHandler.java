package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Queue;

public class HallDoorCloseHandler {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;

    public HallDoorCloseHandler(SmartHome smartHome, Queue<SensorCommand> sensorCommandQueue) {
        this.smartHome = smartHome;
        this.sensorCommandQueue = sensorCommandQueue;
    }

    public void lightOffEverywhere() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sensorCommandQueue.add(command);
            }
        }
    }
}
