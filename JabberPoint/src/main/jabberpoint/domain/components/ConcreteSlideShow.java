package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.SlideShowIterator;
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
        this.componentList.add(slide);
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
     * Returns the componentList containing the slides
     * @return List of its slides
     */
    @Override
    public List<SlideShowComponent> getContent()
    {
        return super.componentList;
    }
}
