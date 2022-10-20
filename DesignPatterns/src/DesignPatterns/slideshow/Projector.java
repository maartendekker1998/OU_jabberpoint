package DesignPatterns.slideshow;

import java.util.ArrayList;
import java.util.List;

public class Projector
{
    private Iterator slideShowIterator;
    private Iterator slideIterator;

    public Projector(Iterable slideShowComponent)
    {
        this.slideShowIterator = slideShowComponent.createIterator();
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
        this.slideIterator.next();
        return this.slideIterator.current();
    }

    public void previousContent()
    {
        this.slideIterator.previous();
    }

    public void noContent()
    {
        this.slideIterator.resetIndex();
    }

    public List<SlideShowComponent> allContent()//ContentList.class
    {
        List<SlideShowComponent> list = new ArrayList<>();
        for (; this.slideIterator.isDone(); this.slideIterator.next())
        {
            list.add(slideIterator.current());
        }
        return list;
    }

    void controller()
    {
        a(allContent());
        a(nextContent());
    }

    void a(SlideShowComponent slideShowComponent)
    {

    }

    void a(List<SlideShowComponent> slideShowComponents)
    {

    }



    public SlideShowComponent getNextSlide()
    {
        this.slideShowIterator.next();
        SlideShowComponent slide = this.slideShowIterator.current();
        this.slideIterator = slide.createIterator();
        return slide;
    }
}
