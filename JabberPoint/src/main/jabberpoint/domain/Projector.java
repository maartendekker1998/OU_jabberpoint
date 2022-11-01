package main.jabberpoint.domain;

import main.jabberpoint.domain.components.SlideShowComponent;
import main.jabberpoint.domain.iterators.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * The Projector class provides the means of interfacing with the slideshow and the content of its slides
 *
 * Part of the Iterator Pattern
 * Role : Client
 */
public class Projector
{
    private Iterator slideShowIterator;
    private Iterator slideIterator;

    /**
     * Sets the slideshow iterator that the projector will interface with
     * @param slideshow slideshow to get the iterator from
     */
    public void setSlideShow(SlideShowComponent slideshow){
        this.slideShowIterator = slideshow.createIterator();
    }

    /**
     * Fetches the next slide
     * @return a slide if there is a next slide or null if there is no next slide
     */
    public SlideShowComponent getNextSlide()
    {
        if (this.slideShowIterator.isDone()) return null;
        this.slideShowIterator.next();
        SlideShowComponent slide = this.slideShowIterator.current();
        this.slideIterator = slide.createIterator();
        return slide;
    }

    /**
     * Fetches the previous slide
     * @return The previous slide
     */
    public SlideShowComponent getPreviousSlide()
    {
        this.slideShowIterator.previous();
        SlideShowComponent slide = this.slideShowIterator.current();
        this.slideIterator = slide.createIterator();
        return slide;
    }

    /**
     * Fetches the next content item
     * @return a next content item or null if there is no next content item
     */
    public SlideShowComponent getNextContent()
    {
        if (this.slideIterator.isDone()) return null;
        this.slideIterator.next();
        return this.slideIterator.current();
    }

    /**
     * Fetches the current content item
     * @return The current content item if there is no previous slide
     */
    public SlideShowComponent getCurrentContent()
    {
        SlideShowComponent component = this.slideIterator.current();
        this.slideIterator.previous();
        return component;
    }

    /**
     * Resets the index of the SlideIterator
     */
    public void removeAllContent()
    {
        this.slideIterator.resetIndex();
    }

    /**
     * Fetches the content that needs to be rendered to finish the slide
     * @return a list of content
     */
    public List<SlideShowComponent> getRemainingContent()
    {
        List<SlideShowComponent> list = new ArrayList<>();
        while (!this.slideIterator.isDone())
        {
            this.slideIterator.next();
            list.add(this.slideIterator.current());
        }
        return list;
    }
}
