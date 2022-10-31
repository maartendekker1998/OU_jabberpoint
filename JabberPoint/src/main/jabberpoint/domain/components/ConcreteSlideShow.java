package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.SlideShowIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Composite Pattern
 * Role: Concrete SlideShowComposite
 */
public class ConcreteSlideShow extends SlideShowComposite
{
    /**
     * Adds a slide to the slideshow
     * @param slide Concrete slide to add
     */
    public void addSlide(ConcreteSlide slide){
        super.componentList.add(slide);
    }

    /**
     * Creates an iterator to iterate over the slides
     * @return SlideShowIterator
     */
    @Override
    public SlideShowIterator createIterator()
    {
        return new SlideShowIterator(this);
    }

    /**
     * Returns a new empty list, this function will not be used on the ConcreteSlideShow since its
     * content will be accessed through the iterator
     * @return empty list
     */
    @Override
    public List<Content> getContent()
    {
        return new ArrayList<>();
    }
}
