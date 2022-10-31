package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to load next slide
 */
public class NextSlideCommand implements Command
{
    /**
     * Triggers the Controller to load next slide
     */
    @Override
    public void execute()
    {
        Controller.getInstance().getNextSlide();
    }
}
