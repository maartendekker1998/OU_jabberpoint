package DesignPatterns.slideshow;

import java.util.List;

public class Slide extends SlideShowComposite
{
    public Slide(List<SlideItem> content) {
        super();
        super.componentList.addAll(content);
    }

    @Override
    String display()
    {
        StringBuilder json = new StringBuilder();
        json.append("<data>");
        for (SlideShowComponent slideShowComponent : super.componentList)
        {
            json.append(slideShowComponent.display());
        }
        json.append("</data>");
        return json.toString();
    }

    @Override
    public Iterator createIterator()
    {
        return null;
    }
}
