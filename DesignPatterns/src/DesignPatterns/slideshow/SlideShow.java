package DesignPatterns.slideshow;

import java.util.List;

public class SlideShow
{
    SlideShowComponent component;
    Iterator iterator;

    public SlideShow(List<Slide> slides)
    {
        component = new SlideShowConcrete(slides);
    }

    public void start()
    {
        iterator = component.createIterator();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        for (SlideShowComponent l = iterator.first(); !iterator.isDone(); l = iterator.next())
        {
            stringBuilder.append(l.display());
        }
        stringBuilder.append("</body></html>");
        System.out.println(stringBuilder.toString());
    }
}