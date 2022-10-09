package DesignPatterns.command;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command
{
    private List<Command> commands = new ArrayList<>();

    public void add(Command command)
    {
        this.commands.add(command);
    }

    @Override
    public void execute()
    {
        System.out.println("Macro executing...");
        this.commands.forEach(Command::execute);
        System.out.println("Macro executed");
    }
}
