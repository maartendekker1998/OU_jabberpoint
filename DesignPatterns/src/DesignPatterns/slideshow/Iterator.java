package DesignPatterns.slideshow;

public interface Iterator
{
    void next();
    void previous();
    boolean isDone();
    void resetIndex();
    SlideShowComponent current();

    SlideShowComponent first();
}
