package main.jabberpoint.userinterface.command;

import main.jabberpoint.control.Controller;

/**
 * Part of Command Pattern
 * Role: Concrete Command to load a new presentation file
 */
public class OpenFileCommand implements Command
{
    private final String file;

    /**
     * Creates an instance of OpenFileCommand and initiates an file location
     * @param file the name of the file to be opened
     */
    public OpenFileCommand(String file){
        this.file = file;
    }

    /**
     * Triggers the Controller to load a new presentation file
     */
    @Override
    public void execute()
    {
        Controller.getInstance().loadFile(file);
    }
}
