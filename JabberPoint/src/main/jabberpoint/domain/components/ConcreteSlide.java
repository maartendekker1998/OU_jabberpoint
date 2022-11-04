package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.SlideIterator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Part of Iterator Pattern
 * Role: ConcreteAggregate
 *
 * Part of Composite Pattern
 * Role: Concrete Composite
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
        super.componentList.add(content);
    }

    /**
     * Calls getContent function to all its children to return the content of the slide
     * @return List of content
     */
    @Override
    public List<SlideShowComponent> getContent()
    {
        List<SlideShowComponent> contentList = new ArrayList<>();
        super.componentList.forEach(content -> contentList.addAll(content.getContent()));
        return contentList;
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
