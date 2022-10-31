package main.jabberpoint.userinterface.command;

/**
 * Part of Command Pattern
 * Role: Command interface for providing an execute function
 */
public interface Command
{
    /**
     * Can execute various commands, this is further defined in its concrete variants
     */
    void execute();
}
