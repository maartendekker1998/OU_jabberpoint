package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;

public class SwingSlideHandler implements SlideHandler
{
    private WindowHandler windowHandler;

    @Override
    public void renderSlide(ConcreteSlide slide)
    {
        System.out.println(slide.getTitle());
        this.windowHandler.addNode(0, slide.getTitle());
//        if (slide.hasTransitions()) //Uncomment when transitions added
        {
            for (Content<String> content : slide.getContent())
            {
                this.windowHandler.addNode(0, content.getData());
            }
        }
    }

    @Override
    public void setWindowHandler(WindowHandler windowHandler)
    {
        this.windowHandler = windowHandler;
    }
}
