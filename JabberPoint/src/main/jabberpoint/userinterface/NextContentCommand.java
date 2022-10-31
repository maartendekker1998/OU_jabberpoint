package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to load next content
 */
public class NextContentCommand implements Command
{
    /**
     * Triggers the Controller to load next content
     */
    @Override
    public void execute()
    {
        Controller.getInstance().getNextContent();
    }
}