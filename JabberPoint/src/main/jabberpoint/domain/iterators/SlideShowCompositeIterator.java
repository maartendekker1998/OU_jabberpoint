package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.SlideShowComposite;

public abstract class SlideShowCompositeIterator implements Iterator
{
    protected int index = -1;
    protected SlideShowComposite iterable;
}
