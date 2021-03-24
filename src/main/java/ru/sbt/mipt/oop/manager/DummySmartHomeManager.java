package ru.sbt.mipt.oop.manager;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.*;
import ru.sbt.mipt.oop.decorators.EventHandlerDecorator;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.EventReceiver;

import java.util.*;

public class DummySmartHomeManager implements SmartHomeManager{
    private SmartHome smartHome;
    private EventReceiver eventReceiver;
    private Queue<SensorCommand> sensorCommandQueue;
    private List<EventHandlerDecorator> eventHandlers;
    private List<CommandHandler> commandHandlers;


    public DummySmartHomeManager(
            SmartHome smartHome,
            EventReceiver eventReceiver,
            Queue<SensorCommand> sensorCommandQueue,
            List<EventHandlerDecorator> eventHandlers,
            List<CommandHandler> commandHandlers
    ) {
        this.smartHome = smartHome;
        this.eventReceiver = eventReceiver;
        this.sensorCommandQueue = sensorCommandQueue;
        this.eventHandlers = eventHandlers;
        this.commandHandlers = commandHandlers;
    }

    @Override
    public void runSmartManager() {
        Event event = eventReceiver.getNextEvent();
        while (event != null) {
            handleEvent(event);
            handleCommand();
            event = eventReceiver.getNextEvent();
        }
    }

    private void handleEvent(Event event) {
        System.out.println("Got event: " + event);
        for (EventHandlerDecorator handler : eventHandlers) {
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
