package DesignPatterns.decorator;

public abstract class Decorator implements VisualComponent
{
    private VisualComponent visualComponent;

    Decorator(VisualComponent visualComponent)
    {
        this.visualComponent = visualComponent;
    }

    @Override
    public void draw()
    {
        this.visualComponent.draw();
    }

    @Override
    public void resize()
    {
        this.visualComponent.resize();
    }
}
