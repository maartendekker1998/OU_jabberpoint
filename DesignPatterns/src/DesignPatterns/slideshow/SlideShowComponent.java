package DesignPatterns.slideshow;

import java.util.List;

public abstract class SlideShowComponent implements Iterable
{
    public abstract List<? extends Content> getContent();
}


