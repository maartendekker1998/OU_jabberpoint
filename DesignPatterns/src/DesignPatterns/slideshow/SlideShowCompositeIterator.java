package DesignPatterns.slideshow;

public abstract class SlideShowCompositeIterator implements Iterator
{
    protected int index = 0;
    protected SlideShowComposite iterable;
}
