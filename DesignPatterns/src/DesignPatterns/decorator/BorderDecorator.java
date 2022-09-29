package DesignPatterns.decorator;

public class BorderDecorator extends Decorator
{
    private int width;

    BorderDecorator(VisualComponent visualComponent, int width)
    {
        super(visualComponent);
        this.width = width;
    }

    private void drawBorder(int width)
    {
        //draw border somehow
    }

    @Override
    public void draw()
    {
        super.draw();
        this.drawBorder(this.width);
    }
}
