package main.jabberpoint.domain.components;

import main.jabberpoint.domain.iterators.Iterator;

public abstract class Content<T> extends SlideShowComponent
{
    private final T data;

    private final int indentation;

    Content(int indentation, T data)
    {
        this.indentation = indentation;
        this.data = data;
    }

    @Override
    public final Iterator createIterator()
    {
        return null;
    }

    public final T getData()
    {
        return this.data;
    }

    public int getIndentation()
    {
        return this.indentation;
    }
}