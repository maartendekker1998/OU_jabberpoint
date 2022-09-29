package DesignPatterns.decorator;

public class ScrollDecorator extends Decorator
{

    ScrollDecorator(VisualComponent visualComponent)
    {
        super(visualComponent);
    }

    private void scrollTo()
    {
        //do some scrolling
    }

    @Override
    public void draw()
    {
        super.draw();
        this.scrollTo();
    }
}