package main.jabberpoint.userinterface.command;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to remove last content
 */
public class RemoveLastContentCommand implements Command
{
    /**
     * Triggers the Controller to remove last content
     */
    @Override
    public void execute()
    {
        Controller.getInstance().removeLastContent();
    }
}