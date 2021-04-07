package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;

public class RemoteControlImpl implements RemoteControl {
    HashMap<String, Command> commandByButton;

    public RemoteControlImpl(HashMap<String, Command> commandByButton) {
        this.commandByButton = commandByButton;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        commandByButton.get(buttonCode).execute();
    }

    public void setCommandForButton(String buttonCode, Command command) {
        if (commandByButton.containsKey(buttonCode)) {
            commandByButton.put(buttonCode, command);
        }
    }
}
