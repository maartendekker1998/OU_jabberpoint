package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.Text;

public class SwingSlideHandler implements SlideHandler
{
    private WindowHandler windowHandler;

    @Override
    public void renderSlide(ConcreteSlide slide)
    {
        System.out.println(slide.getTitle());
        this.windowHandler.clear();
        this.windowHandler.setTitle(slide.getTitle());
//        if (!slide.hasTransitions()) //Uncomment when transitions added
        {
            for (Content content : slide.getContent())
            {
                if (content instanceof Text)
                {
                    this.windowHandler.addNode((Text)content);
                }
            }
        }
    }

    @Override
    public void setWindowHandler(WindowHandler windowHandler)
    {
        this.windowHandler = windowHandler;
    }
}
