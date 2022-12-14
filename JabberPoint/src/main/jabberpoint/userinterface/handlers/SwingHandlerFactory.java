package main.jabberpoint.userinterface.handlers;

/**
 * Part of Abstract Factory Pattern
 * Role: Concrete HandlerFactory to create Swing related handlers
 */
public class SwingHandlerFactory implements HandlerFactory
{
    /**
     * Creates a new SwingSlideHandler
     * @return an instance of a SwingSlideHandler
     */
    @Override
    public SlideHandler createSlideHandler()
    {
        return new SwingSlideHandler();
    }

    /**
     * Creates a SwingWindowHandler with a SwingEventHandler as parameter
     * @return an instance of a SwingWindowHandler with therein a SwingMenuHandler
     * @see SwingWindowHandler
     * @see SwingMenuHandler
     */
    @Override
    public WindowHandler createWindowHandler()
    {
        return new SwingWindowHandler(new SwingMenuHandler(new SwingEventHandler()));
    }
}