package main.jabberpoint.userinterface;

import main.jabberpoint.domain.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Part of Abstract Factory Pattern
 * Role: Concrete class that will be created by the HandleFactory
 * @see HandlerFactory
 * Concrete Swing related SlideHandler that communicates to the WindowHandler, this class is responsible for handling
 * the content items so the UI can actually render them
 */
public class SwingSlideHandler implements SlideHandler
{
    private WindowHandler windowHandler;

    /**
     * Setter for the windowHandler
     * @param windowHandler Can be of type SwingWindowHandler for example
     */
    @Override
    public void setWindowHandler(WindowHandler windowHandler)
    {
        this.windowHandler = windowHandler;
    }

    /**
     * Prepares the windowHandler with information about the slide to render
     * @param slide Contains information about the slide
     */
    @Override
    public void renderSlide(ConcreteSlide slide)
    {
        this.windowHandler.clear(true);
        this.windowHandler.setTitle(slide);
        this.windowHandler.setTransitions(slide.hasTransitions());
        if (!slide.hasTransitions()) this.renderContent(slide.getContent());
    }

    /**
     * Can be called to render only one content item at a time.
     * This function is overloaded
     * @param content Contains one type of Content
     */
    @Override
    public void renderContent(Content content)
    {
        if (content == null) return;
        List<Content> contentList = new ArrayList<>();
        contentList.add(content);
        this.renderContent(contentList);
    }

    /**
     * Gives content to the windowHandler so it can be rendered there
     * This function calls itself recursively if the content is of type BulletList
     * This function is overloaded
     * @param contentList Contains various types of Content
     */
    private void renderContent(List<Content> contentList)
    {
        for (Content content : contentList)
        {
            if (content instanceof Text) this.windowHandler.addText((Text)content);
            if (content instanceof Image) this.windowHandler.addImage((Image)content);
            if (content instanceof BulletList)
            {
                for (Content bulletContent : content.getContent())
                {
                    Map<String, String> style = new HashMap<>();
                    style.put("bullet", content.getStyles().get("bullet") == null ? "-" : content.getStyles().get("bullet"));
                    bulletContent.addStyles(style);
                }
                this.renderContent(((ContentComposite)content).getData());
            }
            if (content instanceof ContentList) this.renderContent(((ContentComposite)content).getData());
        }
    }

    /**
     * Removes the last added content, if this is of type BulletList, it will also remove all its children
     * @param content can be of type BulletList or single content
     */
    @Override
    public void removeContent(ContentList content)
    {
        this.windowHandler.removeLastContent(content);
    }

    /**
     * Removes all rendered content
     */
    @Override
    public void removeAllContent()
    {
        this.windowHandler.clear(false);
    }
}
