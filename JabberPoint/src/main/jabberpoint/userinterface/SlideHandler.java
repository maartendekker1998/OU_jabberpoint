package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;

public interface SlideHandler
{
    void renderSlide(ConcreteSlide slide);
    void setWindowHandler(WindowHandler windowHandler);
    void renderContent(Content content);
    void removeContent(Content content);
    void removeAllContent();
}