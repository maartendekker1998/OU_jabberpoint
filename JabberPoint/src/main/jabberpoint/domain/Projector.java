package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.List;

public class Projector
{
    private Iterator slideShowIterator;
    private Iterator slideIterator;

    public void setSlideShow(SlideShowComponent slideshow){
        this.slideShowIterator = slideshow.createIterator();
    }

    public SlideShowComponent nextContent()
    {
        if (this.slideIterator.isDone()) return null;
        this.slideIterator.next();
        return this.slideIterator.current();
    }

    public SlideShowComponent previousContent()
    {
        SlideShowComponent component = this.slideIterator.current();
        this.slideIterator.previous();
        return component;
    }

    public void removeContent()
    {
        this.slideIterator.resetIndex();
    }

    public List<SlideShowComponent> allContent()
    {
        List<SlideShowComponent> list = new ArrayList<>();
        while (!this.slideIterator.isDone())
        {
            this.slideIterator.next();
            list.add(this.slideIterator.current());
        }
        return list;
    }

    public SlideShowComponent getNextSlide()
    {
        if (this.slideShowIterator.isDone()) return null;
        this.slideShowIterator.next();
        SlideShowComponent slide = this.slideShowIterator.current();
        this.slideIterator = slide.createIterator();
        return slide;
    }

    public SlideShowComponent previousSlide()
    {
        this.slideShowIterator.previous();
        SlideShowComponent slide = this.slideShowIterator.current();
        this.slideIterator = slide.createIterator();
        return slide;
    }
}
