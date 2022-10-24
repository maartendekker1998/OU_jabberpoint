package userinterface;

public interface HandlerFactory
{
    EventHandler createEventHandler();
    SlideHandler createSlideHandler();
    WindowHandler createWindowHandler();
}
