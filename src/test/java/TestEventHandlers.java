import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.CommandHandler;
import ru.sbt.mipt.oop.commands.handlers.LightOffCommandHandler;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.home.*;
import ru.sbt.mipt.oop.home.action.FindDoorAction;
import ru.sbt.mipt.oop.home.action.FindLightAction;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;


import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;


public class TestEventHandlers {
    private SmartHome smartHome;
    private Queue<SensorCommand> sensorCommandQueue;
    private List<CommandHandler> commandHandlers;

    @BeforeEach
    void setUp() {
        JSONObjectStateReader reader = new JSONObjectStateReader("/home/tanya/MIPT/podasd/test/smart-home-2021/smart-home-1.js");
        smartHome = reader.readObject(SmartHome.class);
        sensorCommandQueue = new LinkedList<>();
        CommandHandler[] _commandHandlers = { new LightOffCommandHandler() };
        commandHandlers = Arrays.asList(_commandHandlers);
    }

    @Test
    public void testOpenDoorEventHandler(){
        setUp();
        DoorEventHandler handler = new DoorEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(EventType.DOOR_OPEN, "1");

        handler.handleEvent(event);

        FindDoorAction action = new FindDoorAction("1");
        smartHome.execute(action);
        Door door = action.getResult();
        assertTrue(door.isOpen());
    }

    @Test
    public void testCloseDoorEventHandler(){
        setUp();
        DoorEventHandler handler = new DoorEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "3");

        handler.handleEvent(event);

        FindDoorAction action = new FindDoorAction("3");
        smartHome.execute(action);
        Door door = action.getResult();
        assertFalse(door.isOpen());
    }

    @Test
    public void testOnLightEventHandler(){
        setUp();
        LightEventHandler handler = new LightEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(EventType.LIGHT_ON, "4");

        handler.handleEvent(event);

        FindLightAction action = new FindLightAction("4");
        smartHome.execute(action);
        Light light = action.getResult();
        assertTrue(light.isOn());
    }

    @Test
    public void testOffLightEventHandler(){
        setUp();
        LightEventHandler handler = new LightEventHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(EventType.LIGHT_OFF, "3");

        handler.handleEvent(event);

        FindLightAction action = new FindLightAction("3");
        smartHome.execute(action);
        Light light = action.getResult();
        assertFalse(light.isOn());
    }

    @Test
    public void testHallDoorCloseEventHandler(){
        setUp();
        HallDoorCloseHandler handler = new HallDoorCloseHandler(smartHome, sensorCommandQueue);
        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "4");

        handler.handleEvent(event);
        // обработка комманд созданных обрабочиком
        while (!sensorCommandQueue.isEmpty()) {
            SensorCommand command = sensorCommandQueue.poll();
            for (CommandHandler commandHandler : commandHandlers) {
                commandHandler.doAction(command);
            }
        }

        List<String> lightId = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        for (String id : lightId) {
            FindLightAction action = new FindLightAction(id);
            smartHome.execute(action);
            Light light = action.getResult();
            assertFalse(light.isOn());
        }
    }
}
