package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to load the previous slide
 */
public class PreviousSlideCommand implements Command
{
    /**
     * Triggers the Controller to load previous slide
     */
    @Override
    public void execute()
    {
        Controller.getInstance().getPreviousSlide();
    }
}