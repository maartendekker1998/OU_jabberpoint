package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class RemoveLastContentCommand implements Command
{
    @Override
    public void execute()
    {
        Controller.getInstance().removeLastContent();
    }
}