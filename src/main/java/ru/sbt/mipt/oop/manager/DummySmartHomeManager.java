package ru.sbt.mipt.oop.manager;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.LightOffCommandHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.DoorCloseHandler;
import ru.sbt.mipt.oop.events.handlers.DoorOpenHandler;
import ru.sbt.mipt.oop.events.handlers.LightOffHandler;
import ru.sbt.mipt.oop.events.handlers.LightOnHandler;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.EventReceiver;

import java.util.LinkedList;
import java.util.Queue;

public class DummySmartHomeManager implements SmartHomeManager{
    private SmartHome smartHome;
    private EventReceiver eventReceiver;
    private Queue<SensorCommand> sensorCommandQueue;

    private LightOnHandler lightOnHandler;
    private LightOffHandler lightOffHandler;
    private DoorCloseHandler doorCloseHandler;
    private DoorOpenHandler doorOpenHandler;

    private LightOffCommandHandler lightOffCommandHandler;


    public DummySmartHomeManager(SmartHome smartHome, EventReceiver eventReceiver) {
        this.smartHome = smartHome;
        this.eventReceiver = eventReceiver;

        this.lightOnHandler = new LightOnHandler(smartHome, sensorCommandQueue);
        this.lightOffHandler = new LightOffHandler(smartHome, sensorCommandQueue);
        this.doorCloseHandler = new DoorCloseHandler(smartHome, sensorCommandQueue);
        this.doorOpenHandler = new DoorOpenHandler(smartHome, sensorCommandQueue);

        this.lightOffCommandHandler = new LightOffCommandHandler(smartHome);

        sensorCommandQueue = new LinkedList<>();
    }

    @Override
    public void runSmartManager() {
        SensorEvent event = eventReceiver.getNextSensorEvent();
        while (event != null) {
            handleEvent(event);
            handleCommand();
            event = eventReceiver.getNextSensorEvent();
        }
    }

    private void handleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        switch (event.getType()) {
            case LIGHT_ON:
                lightOnHandler.doAction(event);
                break;

            case LIGHT_OFF:
                lightOffHandler.doAction(event);
                break;

            case DOOR_OPEN:
                doorOpenHandler.doAction(event);
                break;

            case DOOR_CLOSED:
                doorCloseHandler.doAction(event);
                break;
        }
    }

    private void handleCommand() {
        while (!sensorCommandQueue.isEmpty()) {
            SensorCommand command = sensorCommandQueue.poll();
            switch (command.getType()) {
                case LIGHT_OFF:
                    lightOffCommandHandler.doAction(command);
                    break;
            }
        }
    }
}
