package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.SlideShowComponent;

/**
 * Part of Iterator Pattern
 * Role: Interface to provide functions for a concrete iterator
 */
public interface Iterator
{
    /**
     * Iterates to the next item of the iterator
     */
    void next();

    /**
     * Iterates to the previous item of the iterator
     */
    void previous();

    /**
     * Checks if the iterator is at its highest index and thus determine if it cannot iterate further
     * @return boolean value whether the iterator is done
     */
    boolean isDone();

    /**
     * Resets the index to the start of the iterator
     */
    void resetIndex();

    /**
     * Gets the current item where the index of the iterator is
     * @return current item
     */
    SlideShowComponent current();

    /**
     * Gets the first item of the iterator
     * @return first item
     */
    SlideShowComponent first();
}
