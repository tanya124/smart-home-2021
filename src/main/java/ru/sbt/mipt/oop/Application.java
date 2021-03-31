package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.decorators.EventHandlerDecorator;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.commands.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.DummyEventReceiver;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;
import ru.sbt.mipt.oop.manager.DummySmartHomeManager;
import ru.sbt.mipt.oop.receiver.QueueEventReceiver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        JSONObjectStateReader reader = new JSONObjectStateReader("smart-home-1.js");
        SmartHome smartHome = reader.readObject(SmartHome.class);

        // создаем приёмник событий
        // DummyEventReceiver dummyEventReceiver = new DummyEventReceiver();
        Queue<Event> events = new LinkedList<Event>(){{
            add(new AlarmEvent(EventType.ALARM_ACTIVATE, "123"));
            add(new SensorEvent(EventType.DOOR_OPEN, "1"));
            add(new AlarmEvent(EventType.ALARM_DEACTIVATE, "123"));
            add(new SensorEvent(EventType.DOOR_CLOSED, "1"));
        }};
        QueueEventReceiver receiver = new QueueEventReceiver(events);


        // создаём очередь команд
        Queue<SensorCommand> sensorCommandQueue = new LinkedList<>();

        // создаем обработчики событий
        EventHandler[] _eventHandlers = {
                new EventHandlerDecorator(new LightEventHandler(smartHome, sensorCommandQueue), smartHome.getAlarm()),
                new EventHandlerDecorator(new DoorEventHandler(smartHome, sensorCommandQueue), smartHome.getAlarm()),
                new EventHandlerDecorator(new HallDoorCloseHandler(smartHome, sensorCommandQueue), smartHome.getAlarm()),
                new EventHandlerDecorator(new AlarmHandler(smartHome.getAlarm()), smartHome.getAlarm()),
        };
        List<EventHandler> eventHandlers = Arrays.asList(_eventHandlers);

        // создаём обработчики комманд
        CommandHandler[] _commandHandlers = {
                new LightOffCommandHandler()
        };
        List<CommandHandler> commandHandlers = Arrays.asList(_commandHandlers);

        DummySmartHomeManager smartHomeManager =
                new DummySmartHomeManager(smartHome, receiver, sensorCommandQueue, eventHandlers, commandHandlers);


        // начинаем цикл обработки событий
        smartHomeManager.runSmartManager();
    }
}
