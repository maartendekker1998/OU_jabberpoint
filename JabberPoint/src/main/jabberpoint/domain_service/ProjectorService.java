package domain_service;

import domain.DomainFacade;
import domain.Projector;
import domain.SlideShowComponent;

public class ProjectorService
{
    Projector projector;

    //Done by infra, omitted
    {
        DomainFacade facade = new DomainFacade();
        facade.build();
        this.projector = facade.getProjector();
    }

    public void setSlideShow(SlideShowComponent slideShow)
    {
//        this.projector = new Projector(slideShow);
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