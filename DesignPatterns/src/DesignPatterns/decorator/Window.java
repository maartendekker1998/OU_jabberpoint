package DesignPatterns.decorator;

class Window
{
    private VisualComponent visualComponent;

    void setContents(VisualComponent visualComponent)
    {
        this.visualComponent = visualComponent;
    }
}