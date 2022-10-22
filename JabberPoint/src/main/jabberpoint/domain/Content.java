package domain;

public abstract class Content<T> extends SlideShowComponent
{
    private T data;

    private int indentation;

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
