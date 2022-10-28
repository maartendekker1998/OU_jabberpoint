package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class RemainingContentCommand implements Command
{
    @Override
    public void execute()
    {
        Controller.getInstance().remainingContent();
    }
}
