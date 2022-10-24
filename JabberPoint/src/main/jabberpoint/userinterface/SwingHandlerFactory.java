package main.jabberpoint.userinterface;

public class SwingHandlerFactory implements HandlerFactory
{
//    @Override
//    public EventHandler createEventHandler()
//    {
//        return new SwingEventHandler();
//    }

    @Override
    public SlideHandler createSlideHandler()
    {
        return new SwingSlideHandler();
    }

    @Override
    public WindowHandler createWindowHandler()
    {
        return new SwingWindowHandler(new SwingEventHandler());
    }
}