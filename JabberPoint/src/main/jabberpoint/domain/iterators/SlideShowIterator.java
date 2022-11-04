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
        super.iterable = iterable;
    }

    /**
     * Iterates to the next item of the iterator
     */
    @Override
    public void next()
    {
        if ((super.index+1) >= super.iterable.getContent().size()) return;
        super.index++;
    }

    /**
     * Iterates to the previous item of the iterator
     */
    @Override
    public void previous()
    {
        if ((super.index - 1) < 0) return;
        super.index--;
    }

    /**
     * Checks if the iterator is at its highest index and thus determine if it cannot iterate further
     * @return boolean value whether the iterator is done
     */
    @Override
    public boolean isDone()
    {
        return (super.index+1) >= super.iterable.getContent().size();
    }

    /**
     * Resets the index to the start of the iterator
     */
    @Override
    public void resetIndex()
    {
        super.index = 0;
    }

    /**
     * Gets the current item where the index of the iterator is
     * @return current item or the first item if the index < 0
     */
    @Override
    public SlideShowComponent current()
    {
        if (super.index-1 < 0) return this.first();
        return super.iterable.getContent().get(super.index);
    }

    /**
     * Gets the first item of the iterator
     * @return first item
     */
    @Override
    public SlideShowComponent first()
    {
        return super.iterable.getContent().get(0);
    }
}