package main.jabberpoint.userinterface;

import main.jabberpoint.domain.ConcreteSlide;
import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.Image;
import main.jabberpoint.domain.Text;

public interface WindowHandler
{
    void renderUI();
    void addText(Text text);
    void addImage(Image content);
    void setTitle(ConcreteSlide slide);
    void clear(boolean clearTitle);

    void setTransitions(boolean transitions);
    void removeLastContent(Content content);
}