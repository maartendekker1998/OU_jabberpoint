package main.jabberpoint.userinterface.handlers;

import main.jabberpoint.domain.*;

/**
 * Part of Abstract Factory Pattern
 * Role: Interface to provide functions for a concrete WindowHandler that will be created by the HandleFactory
 * @see HandlerFactory
 */
public interface WindowHandler
{
    /**
     * Sets the window visible, the window is prepared in the constructor
     */
    void renderUI();

    /**
     * Adds a text type content to the slide, this will create a label with styles initiated
     * It also sets the bounds and location of the label
     * @param text a text type content
     */
    void addText(Text text);

    /**
     * Adds an image type content to the slide, this will create a label with styles initiated
     * An image is loaded in the label, it is also possible that the label contains text and an image
     * this is so that the BulletList styles can be set on images as well
     * It also sets the bounds and location of the label
     * If the image provided cannot be found, the user will be notified with a message and a default image will be used in stead
     * @param image an image type content
     * @see BulletList
     */
    void addImage(Image image);

    /**
     * Sets the title of the slide, this is always executed whether the slide has transitions or not
     * @param slide a concrete slide type content
     */
    void setTitle(ConcreteSlide slide);

    /**
     * Clears the slide, this can be provided information whether it should remove of preserve the title
     * @param clearTitle boolean to determine if it should remove the title as well
     */
    void clear(boolean clearTitle);

    /**
     * Sets the eventHandler information if the slide contains transitions or not
     * @param transitions transitions of the slide
     * @see EventHandler
     */
    void setTransitions(boolean transitions);

    /**
     * Removes the last added content from the window
     * @param content Content item to remove
     */
    void removeLastContent(ContentList content);
}