package userinterface;

import domain.ConcreteSlide;

public class SwingSlideHandler implements SlideHandler
{
    private WindowHandler windowHandler;

    @Override
    public void renderSlide(ConcreteSlide slide)
    {
        System.out.println(slide.getTitle());
        this.windowHandler.addNode(0, slide.getTitle());
    }

    @Override
    public void setWindowHandler(WindowHandler windowHandler)
    {
        this.windowHandler = windowHandler;
    }
}
