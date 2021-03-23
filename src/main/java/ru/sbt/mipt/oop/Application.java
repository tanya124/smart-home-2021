package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.commands.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.DummyEventReceiver;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;
import ru.sbt.mipt.oop.manager.DummySmartHomeManager;

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
        DummyEventReceiver dummyEventReceiver = new DummyEventReceiver();

        // создаём очередь команд
        Queue<SensorCommand> sensorCommandQueue = new LinkedList<>();

        // создаем обработчики событий
        EventHandler[] _eventHandlers = {
                new LightEventHandler(smartHome, sensorCommandQueue),
                new DoorEventHandler(smartHome, sensorCommandQueue),
                new HallDoorCloseHandler(smartHome, sensorCommandQueue)
        };
        List<EventHandler> eventHandlers = Arrays.asList(_eventHandlers);

        // создаём обработчики комманд
        CommandHandler[] _commandHandlers = {
                new LightOffCommandHandler()
        };
        List<CommandHandler> commandHandlers = Arrays.asList(_commandHandlers);

        DummySmartHomeManager smartHomeManager =
                new DummySmartHomeManager(smartHome, dummyEventReceiver, sensorCommandQueue, eventHandlers, commandHandlers);


        // начинаем цикл обработки событий
        smartHomeManager.runSmartManager();
    }
}
