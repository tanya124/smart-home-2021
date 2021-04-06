package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.decorators.EventHandlerDecorator;
import ru.sbt.mipt.oop.events.EventType;
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

        manager.registerEventHandler(new EventHandlerAdapter(lightEventHandler(), mapOfSensorEventTypeByCCSSensorEventType()));
        manager.registerEventHandler(new EventHandlerAdapter(doorEventHandler(), mapOfSensorEventTypeByCCSSensorEventType()));
        manager.registerEventHandler(new EventHandlerAdapter(hallDoorEventHandler(), mapOfSensorEventTypeByCCSSensorEventType()));

        return manager;
    }

    @Bean
    SmartHome smartHome() {
        JSONObjectStateReader reader = new JSONObjectStateReader("smart-home-1.js");
        SmartHome smartHome = reader.readObject(SmartHome.class);
        return smartHome;
    }

    @Bean
    EventHandler lightEventHandler() {
        return new EventHandlerDecorator(new LightEventHandler(smartHome()), smartHome().getAlarm());
    }

    @Bean
    EventHandler doorEventHandler() {
        return new EventHandlerDecorator(new DoorEventHandler(smartHome()), smartHome().getAlarm());
    }

    @Bean
    EventHandler hallDoorEventHandler() {
        return new EventHandlerDecorator(new HallDoorCloseHandler(smartHome(), sensorCommandQueue()), smartHome().getAlarm());
    }

    @Bean
    Queue<SensorCommand> sensorCommandQueue() {
        return new LinkedList<>();
    }

    @Bean(name = "mapEvents")
    HashMap<String, EventType> mapOfSensorEventTypeByCCSSensorEventType() {
        return new HashMap<>(){{
            put("LightIsOn", EventType.LIGHT_ON);
            put("LightIsOff", EventType.LIGHT_OFF);
            put("DoorIsOpen", EventType.DOOR_OPEN);
            put("DoorIsClosed", EventType.DOOR_CLOSED);
        }};
    }
}
