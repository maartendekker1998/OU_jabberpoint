package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class PreviousSlideCommand implements Command
{
    @Override
    public void execute()
    {
        Controller.getInstance().previousSlide();
    }
}