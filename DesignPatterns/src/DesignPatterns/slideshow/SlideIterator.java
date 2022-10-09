package DesignPatterns.slideshow;

import java.util.List;

public class SlideIterator implements Iterator
{
    int counter = 0;
    List<SlideShowComponent> components;

    public SlideIterator(List<SlideShowComponent> components)
    {
        this.components = components;
    }

    @Override
    public SlideShowComponent next()
    {
        counter++;
        System.out.println("slide next" + (counter-1));
        return components.get(counter-1);
    }

    @Override
    public SlideShowComponent previous()
    {
        System.out.println("slide prev");
        return components.get(0);
    }

    @Override
    public boolean isDone()
    {
        return counter >= components.size();
    }

    @Override
    public SlideShowComponent first() {
        return components.get(0);
    }
}