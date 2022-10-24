package main.jabberpoint.domain_service;

import main.jabberpoint.domain.Projector;
import main.jabberpoint.domain.SlideShowComponent;

public class ProjectorService
{
    Projector projector = new Projector();

    public void setSlideShow(SlideShowComponent slideShow)
    {
        this.projector.setSlideShow(slideShow);
    }

    public SlideShowComponent getNextSlide()
    {
        return this.projector.getNextSlide();
    }

    public SlideShowComponent getPreviousSlide()
    {
        return this.projector.previousSlide();
    }

    public SlideShowComponent nextContent()
    {
        return null;
    }

    public void removeLastContent()
    {

    }

    public void removeAllContent()
    {

    }

    public void remainingContent()
    {

    }
}