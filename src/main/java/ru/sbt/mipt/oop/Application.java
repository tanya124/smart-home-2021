package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.commands.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.DummyEventReceiver;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;
import ru.sbt.mipt.oop.manager.DummySmartHomeManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        JSONObjectStateReader reader = new JSONObjectStateReader("smart-home-1.js");
        SmartHome smartHome = (SmartHome) reader.readObjectState(SmartHome.class);

        // создаем приёмник событий
        DummyEventReceiver dummyEventReceiver = new DummyEventReceiver();

        // создаём очередь команд
        Queue<SensorCommand> sensorCommandQueue = new LinkedList<>();

        // создаем обработчики событий
        ArrayList<EventHandler> eventHandlers = new ArrayList<EventHandler>() {{
            add(new LightOnEventHandler(smartHome, sensorCommandQueue));
            add(new LightOffEventHandler(smartHome, sensorCommandQueue));
            add(new DoorCloseEventHandler(smartHome, sensorCommandQueue));
            add(new DoorOpenEventHandler(smartHome, sensorCommandQueue));
        }};

        // создаём обработчики комманд
        ArrayList<CommandHandler> commandHandlers = new ArrayList<CommandHandler>() {{
            add(new LightOffCommandCommandHandler(smartHome));
        }};

        DummySmartHomeManager smartHomeManager =
                new DummySmartHomeManager(smartHome, dummyEventReceiver, sensorCommandQueue, eventHandlers, commandHandlers);


        // начинаем цикл обработки событий
        smartHomeManager.runSmartManager();
    }
}
