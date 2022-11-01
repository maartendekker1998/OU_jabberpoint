package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.SlideShowComposite;

/**
 * Part of Iterator Pattern
 * Role: Abstract SlideShowCompositeIterator that implements the Iterator
 */
public abstract class SlideShowCompositeIterator implements Iterator
{
    protected int index = -1;
    protected SlideShowComposite iterable;
}
