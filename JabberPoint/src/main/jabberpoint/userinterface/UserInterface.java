package main.jabberpoint.userinterface;

import main.jabberpoint.domain.components.*;
import main.jabberpoint.userinterface.handlers.HandlerFactory;
import main.jabberpoint.userinterface.handlers.SlideHandler;
import main.jabberpoint.userinterface.handlers.WindowHandler;

import java.util.List;

/**
 * Communicates to the SlideHandler and WindowHandler, this initiates the possibility to render the UI
 */
public class UserInterface
{
    private final SlideHandler slideHandler;
    private final WindowHandler windowHandler;

    /**
     * Creates Handlers through a HandlerFactory, this will also tell the windowHandler to render
     * @param handlerFactory a handlers to create handlers
     * @see HandlerFactory
     */
    public UserInterface(HandlerFactory handlerFactory)
    {
        this.slideHandler = handlerFactory.createSlideHandler();
        this.windowHandler = handlerFactory.createWindowHandler();
        this.slideHandler.setWindowHandler(this.windowHandler);
        this.renderUserInterface();
    }

    /**
     * Tells the windowHandler to render the UI
     */
    private void renderUserInterface()
    {
        this.windowHandler.renderUserInterface();
    }

    /**
     * Tells the slideHandler to render a whole slide
     * @param slide the slide to render, this will be casted to ConcreteSlide
     */
    public void renderSlide(SlideShowComponent slide)
    {
        this.slideHandler.renderSlide((ConcreteSlide)slide);
    }

    /**
     * Tells the slideHandler to render a single content item
     * This function is overloaded
     * @param content the content to render, this will be casted to Content
     */
    public void renderContent(SlideShowComponent content)
    {
        this.slideHandler.renderContent((Content)content);
    }

    /**
     * Tells the slide handler to handle multiple content items
     * This function is overloaded
     * @param contents List of Content to be rendered, this will be iterated through and its chunks be casted
     *                 to ContentList
     */
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

    /**
     * Tells the slideHandler to remove its last added content
     * @param content content to remove, this will be casted to ContentComposite
     */
    public void removeContent(SlideShowComponent content)
    {
        this.slideHandler.removeContent((ContentComposite)content);
    }

    /**
     * Tells the slideHandler to remove all its content
     */
    public void removeAllContent()
    {
        this.slideHandler.removeAllContent();
    }
}
