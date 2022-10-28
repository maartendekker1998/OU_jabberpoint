package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.ContentList;
import main.jabberpoint.domain.SlideShowComponent;

import java.util.List;

public class UserInterface
{
    private HandlerFactory handlerFactory;
//    private EventHandler eventHandler;
    private SlideHandler slideHandler;
    private WindowHandler windowHandler;

    public UserInterface(HandlerFactory handlerFactory)
    {
        this.handlerFactory = handlerFactory;
//        this.eventHandler = this.handlerFactory.createEventHandler();
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

    public void renderContent(SlideShowComponent content)
    {
        this.slideHandler.renderContent((Content)content);
    }

    public void renderContent(List<SlideShowComponent> contents)
    {
        for (SlideShowComponent chunks : contents)
        {
            for (Content content : ((ContentList)chunks).getContent())
            {
                this.slideHandler.renderContent(content);
            }
        }
    }

    public void removeLastContent(SlideShowComponent content)
    {
        this.slideHandler.removeLastContent((Content) content);
    }

    void removeAllContent()
    {

    }
}
