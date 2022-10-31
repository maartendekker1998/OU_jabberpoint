package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.SlideShowIterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSlideShow extends SlideShowComposite
{
    public void addSlide(ConcreteSlide slide){
        super.componentList.add(slide);
    }

    @Override
    public SlideShowIterator createIterator()
    {
        return new SlideShowIterator(this);
    }

    @Override
    public List<Content> getContent()
    {
        return new ArrayList<>();
    }
}
