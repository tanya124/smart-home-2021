package ru.sbt.mipt.oop.manager;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.*;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.EventReceiver;

import java.util.*;

public class DummySmartHomeManager implements SmartHomeManager{
    private SmartHome smartHome;
    private EventReceiver eventReceiver;
    private Queue<SensorCommand> sensorCommandQueue;
    private ArrayList<EventHandler> eventHandlers;
    private ArrayList<CommandHandler> commandHandlers;


    public DummySmartHomeManager(
            SmartHome smartHome,
            EventReceiver eventReceiver,
            Queue<SensorCommand> sensorCommandQueue,
            ArrayList<EventHandler> eventHandlers,
            ArrayList<CommandHandler> commandHandlers
    ) {
        this.smartHome = smartHome;
        this.eventReceiver = eventReceiver;
        this.sensorCommandQueue = sensorCommandQueue;
        this.eventHandlers = eventHandlers;
        this.commandHandlers = commandHandlers;
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
        for (EventHandler handler : eventHandlers) {
            handler.handleEvent(event);
        }
    }

    private void handleCommand() {
        while (!sensorCommandQueue.isEmpty()) {
            SensorCommand command = sensorCommandQueue.poll();
            for (CommandHandler handler : commandHandlers) {
                handler.doAction(command);
            }
        }
    }
}
