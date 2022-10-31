package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to remove all content
 */
public class RemoveAllContentCommand implements Command
{
    /**
     * Triggers the Controller to remove all content
     */
    @Override
    public void execute()
    {
        Controller.getInstance().removeAllContent();
    }
}
