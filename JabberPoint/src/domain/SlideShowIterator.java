package domain;

public class SlideShowIterator extends SlideShowCompositeIterator
{
    SlideShowIterator(ConcreteSlideShow iterable)
    {
        this.iterable = iterable;
    }

    @Override
    public void next()
    {
        this.index++;
        System.out.println("slide next" + (this.index-1));
    }

    @Override
    public void previous()
    {
        this.index--;
        System.out.println("slide prev" + (this.index));
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
        return this.iterable.componentList.get(this.index-1);
    }

    @Override
    public SlideShowComponent first()
    {
        return this.iterable.componentList.get(0);
    }
}