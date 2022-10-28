package main.jabberpoint.userinterface;

import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.Image;
import main.jabberpoint.domain.Text;

public interface WindowHandler
{
    void renderUI();
    void addText(Text text);
    void addImage(Image content);
    void setTitle(String title);
    void clear(boolean clearTitle);

    void setTransitions(boolean transitions);
    void removeLastContent(Content content);
}