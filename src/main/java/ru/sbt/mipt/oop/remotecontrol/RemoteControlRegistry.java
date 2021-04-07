package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;

public class RemoteControlRegistry {
    HashMap<String, RemoteControl> remoteControls = new HashMap<>();

    /**
     * Register remote control with id rcId.
     * When button on a real remote control device is pressed this library will call remoteControl.onButtonPressed(...).
     * @param remoteControl
     * @param rcId
     */
    public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
        // here goes some library code which registers our remote control with given ID (rcId)
        remoteControls.put(rcId, remoteControl);
    }

    public void onButtonPressed(String buttonCode, String rcId) {
        if (remoteControls.containsKey(rcId)){
            remoteControls.get(rcId).onButtonPressed(buttonCode);
        }
    }

    public void changeButtonAction(String buttonCode, String rcId, Command command){
        if (remoteControls.containsKey(rcId) && remoteControls.get(rcId) != null){
            ((RemoteControlImpl)remoteControls.get(rcId)).setCommandForButton(buttonCode, command);
        }
    }

}
