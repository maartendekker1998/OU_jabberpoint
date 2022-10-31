package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.ContentList;
import main.jabberpoint.domain.SlideShowComponent;

import java.util.List;

public class UserInterface
{
    private final SlideHandler slideHandler;
    private final WindowHandler windowHandler;

    public UserInterface(HandlerFactory handlerFactory)
    {
        this.slideHandler = handlerFactory.createSlideHandler();
        this.windowHandler = handlerFactory.createWindowHandler();
        this.slideHandler.setWindowHandler(this.windowHandler);
        this.renderUI();
    }

    private void renderUI()
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
            for (Content content : ((ContentList)chunks).getData())
            {
                this.slideHandler.renderContent(content);
            }
        }
    }

    public void removeContent(SlideShowComponent content)
    {
        this.slideHandler.removeContent((Content) content);
    }

    public void removeAllContent()
    {
        this.slideHandler.removeAllContent();
    }
}
