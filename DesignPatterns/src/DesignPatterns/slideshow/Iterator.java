package DesignPatterns.slideshow;

public interface Iterator
{
    void next();
    void previous();
    boolean isDone();
    SlideShowComponent current();

    SlideShowComponent first();
}
