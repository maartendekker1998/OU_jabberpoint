package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class RemoveAllContentCommand implements Command
{
    @Override
    public void execute()
    {
        Controller.getInstance().removeAllContent();
    }
}
