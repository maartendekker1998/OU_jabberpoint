package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.SlideShowComponent;

import java.util.List;

public class UserInterface
{
    private HandlerFactory handlerFactory;
    private EventHandler eventHandler;
    private SlideHandler slideHandler;
    private WindowHandler windowHandler;

    public UserInterface(HandlerFactory handlerFactory)
    {
        this.handlerFactory = handlerFactory;
        this.eventHandler = this.handlerFactory.createEventHandler();
        this.slideHandler = this.handlerFactory.createSlideHandler();
        this.windowHandler = this.handlerFactory.createWindowHandler();

        this.slideHandler.setWindowHandler(this.windowHandler);
        this.renderUI();
    }

    public void renderUI()
    {
        this.windowHandler.renderUI();
    }

    public void renderSlide(SlideShowComponent slide)
    {
        this.slideHandler.renderSlide((ConcreteSlide)slide);
    }

    void renderContent(SlideShowComponent content)
    {

    }

    void renderContent(List<SlideShowComponent> contents)
    {

    }

    void removeLastContent()
    {

    }

    void removeAllContent()
    {

    }
}
