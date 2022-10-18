package DesignPatterns.slideshow;

public class SlideShowIterator extends SlideShowComponentIterator<SlideShowConcrete>
{
    SlideShowIterator(SlideShowConcrete slideshow)
    {
        this.slideshow = slideshow;
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
        return this.index >= this.slideshow.componentList.size();
    }

    @Override
    public SlideShowComponent current()
    {
        return this.slideshow.componentList.get(this.index-1);
    }

    @Override
    public SlideShowComponent first()
    {
        return this.slideshow.componentList.get(0);
    }
}