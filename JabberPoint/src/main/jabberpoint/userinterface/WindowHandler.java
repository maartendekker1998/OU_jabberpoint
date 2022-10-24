package main.jabberpoint.userinterface;

import main.jabberpoint.domain.Text;

public interface WindowHandler
{
    void renderUI();

    void addNode(Text text);
    void setTitle(String title);

    void clear();
}
