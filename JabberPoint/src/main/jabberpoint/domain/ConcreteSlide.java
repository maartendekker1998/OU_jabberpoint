package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSlide extends SlideShowComposite
{
    private String title;
    private Boolean hasTransitions;

    @Override
    public SlideIterator createIterator()
    {
        return new SlideIterator(this);
    }

    public void addContent(Content content){
        super.componentList.add(content);
    }

    @Override
    public List<Content> getContent()
    {
        List<Content> list = new ArrayList<>();
        super.componentList.forEach(content -> list.add((Content) content));
        return list;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean hasTransitions() {
        return hasTransitions;
    }

    public void setHasTransitions(Boolean hasTransitions) {
        this.hasTransitions = hasTransitions;
    }
}
