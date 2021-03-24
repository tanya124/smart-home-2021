import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.CommandHandler;
import ru.sbt.mipt.oop.commands.handlers.LightOffCommandHandler;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.AlarmHandler;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.FindDoorAction;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.AlarmActiveState;
import ru.sbt.mipt.oop.home.alarm.AlarmDeactivateState;
import ru.sbt.mipt.oop.home.alarm.AlarmSosState;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlarmEvent {
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
    public void testAlarmActivateHandlerWithCorrectCode(){
        setUp();
        AlarmEvent event = new AlarmEvent(EventType.ALARM_ACTIVATE, "123");
        AlarmHandler handler = new AlarmHandler(smartHome);
        handler.handleEvent(event);

        Alarm alarm = smartHome.getAlarm();
        assertTrue(alarm.getState() instanceof AlarmActiveState);
    }

    @Test
    public void testAlarmActivateHandlerWithIncorrectCode(){
        setUp();
        AlarmEvent event = new AlarmEvent(EventType.ALARM_ACTIVATE, "122");
        AlarmHandler handler = new AlarmHandler(smartHome);
        handler.handleEvent(event);

        Alarm alarm = smartHome.getAlarm();
        assertTrue(alarm.getState() instanceof AlarmDeactivateState);
    }

    @Test
    public void testAlarmDeactivateHandlerWithCorrectCode(){
        setUp();
        Alarm alarm = smartHome.getAlarm();
        alarm.setState(new AlarmActiveState(alarm));

        AlarmEvent event = new AlarmEvent(EventType.ALARM_DEACTIVATE, "123");
        AlarmHandler handler = new AlarmHandler(smartHome);
        handler.handleEvent(event);

        assertTrue(alarm.getState() instanceof AlarmDeactivateState);
    }

    @Test
    public void testAlarmDeactivateHandlerWithIncorrectCode(){
        setUp();
        Alarm alarm = smartHome.getAlarm();
        alarm.setState(new AlarmActiveState(alarm));

        AlarmEvent event = new AlarmEvent(EventType.ALARM_DEACTIVATE, "122");
        AlarmHandler handler = new AlarmHandler(smartHome);
        handler.handleEvent(event);

        assertTrue(alarm.getState() instanceof AlarmSosState);
    }
}
