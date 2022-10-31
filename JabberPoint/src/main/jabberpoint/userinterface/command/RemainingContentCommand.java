package main.jabberpoint.userinterface.command;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to load the remaining content
 */
public class RemainingContentCommand implements Command
{
    /**
     * Triggers the Controller to load the remaining content
     */
    @Override
    public void execute()
    {
        Controller.getInstance().getRemainingContent();
    }
}
