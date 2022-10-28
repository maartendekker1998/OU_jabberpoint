package main.jabberpoint.domain;

public class SlideShowIterator extends SlideShowCompositeIterator
{
    SlideShowIterator(ConcreteSlideShow iterable)
    {
        this.iterable = iterable;
    }

    @Override
    public void next()
    {
        if ((this.index+1) >= this.iterable.componentList.size()) return;
        this.index++;
    }

    @Override
    public void previous()
    {
        if ((this.index - 1) < 0) return;
        this.index--;
    }

    @Override
    public boolean isDone()
    {
        return this.index >= this.iterable.componentList.size();
    }

    @Override
    public void resetIndex()
    {
        this.index = 0;
    }

    @Override
    public SlideShowComponent current()
    {
        if (this.index-1 < 0) return this.first();
        return this.iterable.componentList.get(this.index);
    }

    @Override
    public SlideShowComponent first()
    {
        return this.iterable.componentList.get(0);
    }
}