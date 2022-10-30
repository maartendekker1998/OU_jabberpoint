package main.jabberpoint.userinterface;

import main.jabberpoint.control.Controller;

public class OpenFileCommand implements Command
{
    private String file;

    public OpenFileCommand(String file){
        this.file = file;
    }

    @Override
    public void execute()
    {
        Controller.getInstance().loadFile(file);
    }
}
