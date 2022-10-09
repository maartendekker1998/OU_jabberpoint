package DesignPatterns.slideshow;

import java.util.List;

public class SlideShowConcrete extends SlideShowComposite
{
    public SlideShowConcrete(List<Slide> slides)
    {
        super.componentList.addAll(slides);
    }

    @Override
    String display() {
        return "null";
    }

    @Override
    public Iterator createIterator() {
        return new SlideIterator(super.componentList);
    }
}
