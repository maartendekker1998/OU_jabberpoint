package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.List;

public class Projector
{
    private Iterator slideShowIterator;
    private Iterator slideIterator;

    public Projector(){
    }


    public void setSlideShow(SlideShowComponent slideshow){
        this.slideShowIterator = slideshow.createIterator();
    }

    public void start()
    {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("<html><body>");
//        for (this.iterator.first(); !this.iterator.isDone(); this.iterator.next())
//        {
//            stringBuilder.append(this.iterator.current().getContent());
//        }
//        stringBuilder.append("</body></html>");
//        System.out.println(stringBuilder.toString());
    }

    public SlideShowComponent nextContent()//ContentList.class
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

    public List<SlideShowComponent> allContent()//ContentList.class
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
