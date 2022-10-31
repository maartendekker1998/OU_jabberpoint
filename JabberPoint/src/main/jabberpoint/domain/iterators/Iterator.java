package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.SlideShowComponent;

public interface Iterator
{
    void next();
    void previous();
    boolean isDone();
    void resetIndex();
    SlideShowComponent current();
    SlideShowComponent first();
}
