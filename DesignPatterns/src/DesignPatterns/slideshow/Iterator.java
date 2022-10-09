package DesignPatterns.slideshow;

public interface Iterator
{
    SlideShowComponent next();
    SlideShowComponent previous();
    boolean isDone();

    SlideShowComponent first();
}
