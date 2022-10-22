package domain;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSlide extends SlideShowComposite
{
    private String title;

    ConcreteSlide(String title, List<Content> content)
    {
        super();
        this.title = title;
        super.componentList.addAll(content);
    }

    public String getTitle()
    {
        return this.title;
    }

    @Override
    public SlideIterator createIterator()
    {
        return new SlideIterator(this);
    }

    @Override
    public List<Content> getContent()
    {
        List<Content> list = new ArrayList<>();
        super.componentList.forEach(content -> list.addAll(content.getContent()));
        return list;
    }
}
