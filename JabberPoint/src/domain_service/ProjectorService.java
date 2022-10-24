package domain_service;

import domain.Projector;
import domain.SlideShowComponent;

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

    public SlideShowComponent getPriviousSlide()
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