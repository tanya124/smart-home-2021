package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    HashMap<String, Command> commandByButton = new HashMap<>(){{
        put("A", null);
        put("B", null);
        put("C", null);
        put("D", null);
        put("1", null);
        put("2", null);
        put("3", null);
        put("4", null);
    }};

    @Override
    public void onButtonPressed(String buttonCode) {
        commandByButton.get(buttonCode).execute();
    }

    public void setCommandForButton(String buttonCode, Command command) throws ButtonCodeException {
        if (commandByButton.containsKey(buttonCode)) {
            commandByButton.put(buttonCode, command);
        } else {
            throw new ButtonCodeException("Button code is incorrect.");
        }
    }
}
