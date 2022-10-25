package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.SlideShowComponent;

public interface SlideHandler
{
    void renderSlide(ConcreteSlide slide);
    void setWindowHandler(WindowHandler windowHandler);
    void renderContent(Content content);
    void removeLastContent(Content content);
}