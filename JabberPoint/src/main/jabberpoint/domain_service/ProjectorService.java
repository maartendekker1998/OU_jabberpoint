package main.jabberpoint.domain_service;

import main.jabberpoint.domain.Projector;
import main.jabberpoint.domain.SlideShowComponent;

import java.util.List;

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
        return this.projector.nextContent();
    }

    public SlideShowComponent removeLastContent()
    {
        return this.projector.previousContent();
    }

    public void removeAllContent()
    {

    }

    public List<SlideShowComponent> remainingContent()
    {
        return this.projector.allContent();
    }
}