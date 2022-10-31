package main.jabberpoint.userinterface;

/**
 * Part of Abstract Factory Pattern
 * Role: Interface to provide creation functions
 */
public interface HandlerFactory
{
    /**
     * Creates a new SlideHandler
     * @return an instance of a SlideHandler
     */
    SlideHandler createSlideHandler();

    /**
     * Creates a WindowHandler with therein a EventHandler
     * @return an instance of a WindowHandler
     */
    WindowHandler createWindowHandler();
}
