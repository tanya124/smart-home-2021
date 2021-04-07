package ru.sbt.mipt.oop.home.action;


import ru.sbt.mipt.oop.home.Light;

public class LightOnAllHomeAction implements Action {
    @Override
    public void execute(Actionable object) {
        if (object instanceof Light) {
            Light light = (Light) object;
            light.setOn(true);
        }
    }
}
