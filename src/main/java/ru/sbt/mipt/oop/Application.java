package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.receiver.DummyEventReceiver;
import ru.sbt.mipt.oop.reader.JSONObjectStateReaderFromFile;
import ru.sbt.mipt.oop.manager.DummySmartHomeManager;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = (SmartHome) (new JSONObjectStateReaderFromFile()).readObjectState("smart-home-1.js", SmartHome.class);
        DummyEventReceiver dummyEventReceiver = new DummyEventReceiver();
        DummySmartHomeManager smartHomeManager = new DummySmartHomeManager(smartHome, dummyEventReceiver);

        // начинаем цикл обработки событий
        smartHomeManager.runSmartManager();
    }
}
