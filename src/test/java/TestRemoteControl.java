import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.ApplicationConfiguration;

import ru.sbt.mipt.oop.home.Door;
import ru.sbt.mipt.oop.home.Light;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.home.action.FindDoorAction;
import ru.sbt.mipt.oop.home.action.FindLightAction;
import ru.sbt.mipt.oop.home.alarm.Alarm;
import ru.sbt.mipt.oop.remotecontrol.commands.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestRemoteControl {
    private AnnotationConfigApplicationContext context;
    private String hallDoorId = "4";
    private String[] lightsId = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String[] lightsIdInHall = {"7", "8", "9"};

    @BeforeEach
    void setUp() {
        this.context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }

    @Test
    public void testActivateAlarmCommand(){
        setUp();
        ActivateAlarmCommand command = context.getBean(ActivateAlarmCommand.class);
        command.execute();

        Alarm alarm = context.getBean(Alarm.class);
        assertTrue(alarm.alarmIsActivate());
    }

    @Test
    public void testCloseHallDoorCommand() {
        setUp();
        CloseHallDoorCommand command = context.getBean(CloseHallDoorCommand.class);
        command.execute();

        FindDoorAction action = new FindDoorAction(hallDoorId);
        SmartHome smartHome = context.getBean(SmartHome.class);
        smartHome.execute(action);
        Door hallDoor = action.getResult();
        assertFalse(hallDoor.isOpen());
    }

    @Test
    public void testSosAlarmCommand() {
        setUp();
        SosAlarmCommand command = new SosAlarmCommand(context.getBean(Alarm.class));
        command.execute();

        Alarm alarm = context.getBean(Alarm.class);
        assertTrue(alarm.alarmIsSos());
    }

    @Test
    public void testTurnOffAllLightCommand() {
        setUp();
        TurnOffAllLightCommand command = context.getBean(TurnOffAllLightCommand.class);
        command.execute();

        for(String lightId : lightsId) {
            FindLightAction action = new FindLightAction(lightId);
            SmartHome smartHome = context.getBean(SmartHome.class);
            smartHome.execute(action);

            Light light = action.getResult();
            assertFalse(light.isOn());
        }
    }

    @Test
    public void testTurnOnAllLightCommand() {
        setUp();
        TurnOnAllLightCommand command = context.getBean(TurnOnAllLightCommand.class);
        command.execute();

        for(String lightId : lightsId) {
            FindLightAction action = new FindLightAction(lightId);
            SmartHome smartHome = context.getBean(SmartHome.class);
            smartHome.execute(action);

            Light light = action.getResult();
            assertTrue(light.isOn());
        }
    }

    @Test
    public void testTurnOnLightInHallCommand() {
        setUp();
        TurnOnLightInHallCommand command = context.getBean(TurnOnLightInHallCommand.class);
        command.execute();

        for(String lightId : lightsIdInHall) {
            FindLightAction action = new FindLightAction(lightId);
            SmartHome smartHome = context.getBean(SmartHome.class);
            smartHome.execute(action);

            Light light = action.getResult();
            assertTrue(light.isOn());
        }
    }
}
