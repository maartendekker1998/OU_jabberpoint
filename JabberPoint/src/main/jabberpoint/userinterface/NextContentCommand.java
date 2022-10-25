package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class NextContentCommand implements Command
{
    @Override
    public void execute()
    {
        Controller.getInstance().nextContent();
    }
}