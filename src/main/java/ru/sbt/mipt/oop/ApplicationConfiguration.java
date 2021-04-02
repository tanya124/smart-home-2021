package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.decorators.EventHandlerDecorator;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;

import java.util.*;

@Configuration
public class ApplicationConfiguration {

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();

        Queue<SensorCommand> sensorCommandQueue = new LinkedList<>();
        EventHandler[] eventHandlers = {
                new EventHandlerDecorator(new LightEventHandler(smartHome(), sensorCommandQueue), smartHome().getAlarm()),
                new EventHandlerDecorator(new DoorEventHandler(smartHome(), sensorCommandQueue), smartHome().getAlarm()),
                new EventHandlerDecorator(new HallDoorCloseHandler(smartHome(), sensorCommandQueue), smartHome().getAlarm())
        };

        manager.registerEventHandler(new EventHandlerAdapter(eventHandlers[0]));
        manager.registerEventHandler(new EventHandlerAdapter(eventHandlers[1]));
        manager.registerEventHandler(new EventHandlerAdapter(eventHandlers[2]));

        return manager;
    }

    @Bean
    SmartHome smartHome() {
        JSONObjectStateReader reader = new JSONObjectStateReader("smart-home-1.js");
        SmartHome smartHome = reader.readObject(SmartHome.class);
        return smartHome;
    }
}
