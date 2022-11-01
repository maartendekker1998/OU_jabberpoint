package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.SlideIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Concrete SlideShowComposite
 */
public class ConcreteSlide extends SlideShowComposite
{
    private String title;
    private Boolean hasTransitions;

    /**
     * Creates an iterator to iterate over the slide items
     * @return SlideIterator
     */
    @Override
    public SlideIterator createIterator()
    {
        return new SlideIterator(this);
    }

    /**
     * Adds content to the slide
     * @param content Content to add to the slide
     */
    public void addContent(Content content){
        super.getContent().add(content);
    }

    /**
     * Gets the title of the slide
     * @return Title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Sets the title of the slide
     * @param title Title to be set on slide
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the transition possibility of the slide
     * @return Transition
     */
    public boolean hasTransitions() {
        return hasTransitions;
    }

    /**
     * Sets the transition possibility of the slide
     * @param hasTransitions Transition to be set
     */
    public void setHasTransitions(boolean hasTransitions) {
        this.hasTransitions = hasTransitions;
    }
}
