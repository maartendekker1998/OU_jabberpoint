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
    private List<SlideShowComponent> componentList = new ArrayList<>();

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

    @Override
    public List<? extends SlideShowComponent> getContent()
    {
        List<SlideShowComponent> slides = new ArrayList<>();
        for (SlideShowComponent s : componentList)
        {
            slides.add(s);
        }
        return slides;
    }
}
