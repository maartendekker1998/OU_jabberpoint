package userinterface;

import domain.ConcreteSlide;

public interface SlideHandler
{
    void renderSlide(ConcreteSlide slide);
    void setWindowHandler(WindowHandler windowHandler);
}
