package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class NextSlideCommand implements Command
{
    @Override
    public void execute()
    {
        Controller.getInstance().getNextSlide();
    }
}
