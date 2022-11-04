package main.jabberpoint.userinterface.handlers;

import main.jabberpoint.domain.components.ConcreteSlide;
import main.jabberpoint.domain.components.Content;
import main.jabberpoint.domain.components.ContentComposite;

/**
 * Part of Abstract Factory Pattern
 * Role: Interface to provide functions for a concrete SlideHandler that will be created by the HandleFactory
 * @see HandlerFactory
 */
public interface SlideHandler
{
    /**
     * Prepares the windowHandler with information about the slide to render
     * @param slide Contains information about the slide
     */
    void renderSlide(ConcreteSlide slide);

    /**
     * Setter for the windowHandler
     * @param windowHandler Can be of type SwingWindowHandler for example
     */
    void setWindowHandler(WindowHandler windowHandler);

    /**
     * Can be called to render only one content item at a time.
     * This function is overloaded
     * @param content Contains one type of Content
     */
    void renderContent(Content content);

    /**
     * Removes the last added content, if this is of type ContentComposite, it will remove all its children
     * @param content can be of type ContentComposite or single content
     */
    void removeContent(ContentComposite content);

    /**
     * Removes all rendered content
     */
    void removeAllContent();
}