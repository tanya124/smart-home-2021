import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.handlers.CommandHandler;
import ru.sbt.mipt.oop.commands.handlers.LightOffCommandHandler;
import ru.sbt.mipt.oop.decorators.EventHandlerDecorator;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.*;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.home.alarm.AlarmSosState;
import ru.sbt.mipt.oop.manager.DummySmartHomeManager;
import ru.sbt.mipt.oop.reader.JSONObjectStateReader;
import ru.sbt.mipt.oop.receiver.QueueEventReceiver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEventHandlerDecorator {
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
    public void testSosStateAfterActiveState() {
        setUp();
        Queue<Event> events = new LinkedList<Event>(){{
            add(new AlarmEvent(EventType.ALARM_ACTIVATE, "123"));
            add(new SensorEvent(EventType.DOOR_OPEN, "1"));
        }};
        QueueEventReceiver receiver = new QueueEventReceiver(events);

        EventHandler[] _eventHandlers = {
                new EventHandlerDecorator(new LightEventHandler(smartHome, sensorCommandQueue), smartHome.getAlarm()),
                new EventHandlerDecorator(new DoorEventHandler(smartHome, sensorCommandQueue), smartHome.getAlarm()),
                new EventHandlerDecorator(new HallDoorCloseHandler(smartHome, sensorCommandQueue), smartHome.getAlarm()),
                new EventHandlerDecorator(new AlarmHandler(smartHome.getAlarm()), smartHome.getAlarm()),
        };
        List<EventHandler> eventHandlers = Arrays.asList(_eventHandlers);

        DummySmartHomeManager smartHomeManager =
                new DummySmartHomeManager(smartHome, receiver, sensorCommandQueue, eventHandlers, commandHandlers);


        // начинаем цикл обработки событий
        smartHomeManager.runSmartManager();

        Alarm alarm = smartHome.getAlarm();
        assertTrue(alarm.alarmIsSos());
    }
}
