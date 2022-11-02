package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.SlideShowIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Iterator Pattern
 * Role: ConcreteAggregate
 *
 * Part of Composite Pattern
 * Role: Concrete Composite
 */
public class ConcreteSlideShow extends SlideShowComposite
{
    /**
     * Adds a slide to the slideshow
     * @param slide Concrete slide to add
     */
    public void addSlide(ConcreteSlide slide){
        super.getContent().add(slide);
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
}
