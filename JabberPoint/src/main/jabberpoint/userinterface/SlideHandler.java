package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;

public interface SlideHandler
{
    void renderSlide(ConcreteSlide slide);
    void setWindowHandler(WindowHandler windowHandler);
}
