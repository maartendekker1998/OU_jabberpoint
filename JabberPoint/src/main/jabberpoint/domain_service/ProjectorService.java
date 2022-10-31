package main.jabberpoint.domain_service;

import main.jabberpoint.domain.Projector;
import main.jabberpoint.domain.components.SlideShowComponent;

import java.util.List;

/**
 * The ProjectorService is the owner of the domain class Projector, and provides the means of interfacing with this projector.
 */
public class ProjectorService
{
    private final Projector projector = new Projector();

    /**
     * This function inserts a slideshow into the projector
     * @param slideShow
     */
    public void setSlideShow(SlideShowComponent slideShow)
    {
        this.projector.setSlideShow(slideShow);
    }

    /**
     * Fetches the next slide from the projector
     * @return a slide
     */
    public SlideShowComponent getNextSlide()
    {
        return this.projector.getNextSlide();
    }

    /**
     * Fetches the previous slide from the projector
     * @return a slide
     */
    public SlideShowComponent getPreviousSlide()
    {
        return this.projector.getPreviousSlide();
    }

    /**
     * Fetches the next content item from the projector
     * @return a slide
     */
    public SlideShowComponent getNextContent()
    {
        return this.projector.getNextContent();
    }

    /**
     * Fetches the previous content item from the projector
     * @return a slide
     */
    public SlideShowComponent getCurrentContent()
    {
        return this.projector.getCurrentContent();
    }

    /**
     * Tells the projector to reset the index of the slideIterator
     */
    public void removeAllContent()
    {
        this.projector.removeAllContent();
    }

    /**
     * Fetches the remaining content items that need to be rendered to complete the slide
     * @return
     */
    public List<SlideShowComponent> getRemainingContent()
    {
        return this.projector.getRemainingContent();
    }
}