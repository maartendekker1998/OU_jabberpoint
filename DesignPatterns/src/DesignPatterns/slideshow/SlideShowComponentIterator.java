package DesignPatterns.slideshow;

public abstract class SlideShowComponentIterator<T extends SlideShowComponent> implements Iterator
{
    protected int index = 0;
    protected T slideshow;
}
