package DesignPatterns.slideshow;

public class Text extends SlideItem
{
    @Override
    String display()
    {
        //do epic show function
        return "epic data";
    }

    @Override
    public Iterator createIterator()
    {
        return null;
    }
}
