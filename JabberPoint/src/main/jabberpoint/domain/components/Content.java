package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.Iterator;

/**
 * Part of Composite Pattern
 * Role: Abstract Leaf
 *
 * @param <T> This must be set as a type of the data the Content should have
 */
public abstract class Content<T> extends SlideShowComponent
{
    private final T data;
    private final int indentation;

    /**
     * Creates an instance of Content, this is an abstract constructor
     * @param indentation indentation to be set
     * @param data data to be set in the defined data type in the concrete content items
     */
    Content(int indentation, T data)
    {
        this.indentation = indentation;
        this.data = data;
    }

    /**
     * Creates an iterator, inherent from parent, content does not need an iterator and therefor none is created
     * @return null
     */
    @Override
    public final Iterator createIterator()
    {
        return null;
    }

    /**
     * Gets the data of the concrete content item
     * @return the data as the defined data type in the concrete content items
     */
    public final T getData()
    {
        return this.data;
    }

    /**
     * Gets the indentation of the concrete content item
     * @return the indentation of the concrete content item
     */
    public int getIndentation()
    {
        return this.indentation;
    }
}
