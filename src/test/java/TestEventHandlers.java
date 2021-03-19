import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.CommandHandler;
import ru.sbt.mipt.oop.commands.handlers.LightOffCommandHandler;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.iterator.RoomSmartIterator;
import ru.sbt.mipt.oop.home.iterator.SmartHomeSmartIterator;
import ru.sbt.mipt.oop.home.iterator.SmartIterator;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;


public class TestEventHandlers {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;
    private ArrayList<CommandHandler> commandHandlers;

    @BeforeEach
    void setUp() {
        JSONObjectStateReader reader = new JSONObjectStateReader("/home/tanya/MIPT/podasd/test/smart-home-2021/smart-home-1.js");
        smartHome = reader.readObject(SmartHome.class);
        sensorCommandQueue = new LinkedList<>();
        commandHandlers = new ArrayList<CommandHandler>() {{
            add(new LightOffCommandHandler());
        }};
    }

    @Test
    public void testOpenDoorEventHandler(){
        setUp();
        DoorEventHandler handler = new DoorEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

        handler.handleEvent(event);

        SmartIterator iterator = smartHome.createIterator();
        while (iterator.hasMore()) {
            Room room = iterator.getNext();
            SmartIterator roomIterator = room.createIterator();
            while(roomIterator.hasMore()) {
                Device device = roomIterator.getNext();
                if (device instanceof Door) {
                    Door door = (Door) device;
                    if (door.getId().equals(event.getObjectId())) {
                        assertTrue(door.isOpen());
                    }
                }
            }
        }
    }

    @Test
    public void testCloseDoorEventHandler(){
        setUp();
        DoorEventHandler handler = new DoorEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");

        handler.handleEvent(event);

        SmartIterator iterator = smartHome.createIterator();
        while (iterator.hasMore()) {
            Room room = iterator.getNext();
            SmartIterator roomIterator = room.createIterator();
            while(roomIterator.hasMore()) {
                Device device = roomIterator.getNext();
                if (device instanceof Door) {
                    Door door = (Door) device;
                    if (door.getId().equals(event.getObjectId())) {
                        assertFalse(door.isOpen());
                    }
                }
            }
        }
    }

    @Test
    public void testOnLightEventHandler(){
        setUp();
        LightEventHandler handler = new LightEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "4");

        handler.handleEvent(event);

        SmartIterator iterator = smartHome.createIterator();
        while (iterator.hasMore()) {
            Room room = iterator.getNext();
            SmartIterator roomIterator = room.createIterator();
            while(roomIterator.hasMore()) {
                Device device = roomIterator.getNext();
                if (device instanceof Light) {
                    Light light = (Light) device;
                    if (light.getId().equals(event.getObjectId())) {
                        assertTrue(light.isOn());
                    }
                }
            }
        }
    }

    @Test
    public void testOffLightEventHandler(){
        setUp();
        LightEventHandler handler = new LightEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "3");

        handler.handleEvent(event);

        SmartIterator iterator = smartHome.createIterator();
        while (iterator.hasMore()) {
            Room room = iterator.getNext();
            SmartIterator roomIterator = room.createIterator();
            while(roomIterator.hasMore()) {
                Device device = roomIterator.getNext();
                if (device instanceof Light) {
                    Light light = (Light) device;
                    if (light.getId().equals(event.getObjectId())) {
                        assertFalse(light.isOn());
                    }
                }
            }
        }
    }

    @Test
    public void testHallDoorCloseEventHandler(){
        setUp();
        HallDoorCloseHandler handler = new HallDoorCloseHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");

        handler.handleEvent(event);
        // обработка комманд созданных обрабочиком
        while (!sensorCommandQueue.isEmpty()) {
            SensorCommand command = sensorCommandQueue.poll();
            for (CommandHandler commandHandler : commandHandlers) {
                commandHandler.doAction(command);
            }
        }

        SmartIterator iterator = smartHome.createIterator();
        while (iterator.hasMore()) {
            Room room = iterator.getNext();
            SmartIterator roomIterator = room.createIterator();
            while(roomIterator.hasMore()) {
                Device device = roomIterator.getNext();
                if (device instanceof Light) {
                    Light light = (Light) device;
                    assertFalse(light.isOn());
                }
            }
        }
    }
}
