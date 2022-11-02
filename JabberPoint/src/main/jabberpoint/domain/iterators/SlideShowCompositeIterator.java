package main.jabberpoint.domain.iterators;

import main.jabberpoint.domain.components.SlideShowComposite;

/**
 * Part of Iterator Pattern
 * Role: Abstract Iterator
 */
abstract class SlideShowCompositeIterator implements Iterator
{
    int index = -1;
    SlideShowComposite iterable;
}
