package DesignPatterns.slideshow;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSlide extends SlideShowComposite
{
    private String title;

    ConcreteSlide(List<Content> content)
    {
        super();
        super.componentList.addAll(content);
    }

    @Override
    public Iterator createIterator()
    {
        return null;
    }

    @Override
    public List<Content> getContent()
    {
        List<Content> list = new ArrayList<>();
        super.componentList.forEach(content -> list.addAll(content.getContent()));
        return list;
    }
}
