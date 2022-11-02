package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.ConcreteSlideShow;
import main.jabberpoint.domain.components.SlideShowComponent;

/**
 * Part of Iterator Pattern
 * Role: Concrete Iterator
 */
public class SlideShowIterator extends SlideShowCompositeIterator
{
    public SlideShowIterator(ConcreteSlideShow iterable)
    {
        this.iterable = iterable;
    }

    /**
     * Iterates to the next item of the iterator
     */
    @Override
    public void next()
    {
        if ((this.index+1) >= this.iterable.getContent().size()) return;
        this.index++;
    }

    /**
     * Iterates to the previous item of the iterator
     */
    @Override
    public void previous()
    {
        if ((this.index - 1) < 0) return;
        this.index--;
    }

    /**
     * Checks if the iterator is at its highest index and thus determine if it cannot iterate further
     * @return boolean value whether the iterator is done
     */
    @Override
    public boolean isDone()
    {
        return (this.index+1) >= this.iterable.getContent().size();
    }

    /**
     * Resets the index to the start of the iterator
     */
    @Override
    public void resetIndex()
    {
        this.index = 0;
    }

    /**
     * Gets the current item where the index of the iterator is
     * @return current item or the first item if the index < 0
     */
    @Override
    public SlideShowComponent current()
    {
        if (this.index-1 < 0) return this.first();
        return this.iterable.getContent().get(this.index);
    }

    /**
     * Gets the first item of the iterator
     * @return first item
     */
    @Override
    public SlideShowComponent first()
    {
        return this.iterable.getContent().get(0);
    }
}