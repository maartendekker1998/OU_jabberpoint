package DesignPatterns.slideshow;

public class DomainFacade
{
    private Projector projector;
    private Director director;
    private Builder builder;

    public void build()
    {
        this.director = new Director();
        this.builder = new SlideShowComponentBuilder();
        this.director.constructSlideShow(this.builder);
        this.projector = new Projector(this.builder.getSlideShow());
    }

    public Projector getProjector()
    {
        return this.projector;
    }
}
