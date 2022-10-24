package main.jabberpoint.userinterface;

import java.awt.*;

public class MenuController extends MenuBar
{
    public MenuController(SwingEventHandler eventHandler)
    {
        Menu menu = new Menu("Presentation");
        MenuItem nextSlide = new MenuItem("Next", new MenuShortcut('N'));
        nextSlide.addActionListener(actionEvent -> eventHandler.menuItemClick(actionEvent.getActionCommand()));
        menu.add(nextSlide);

        MenuItem previousSlide = new MenuItem("Previous", new MenuShortcut('P'));
        previousSlide.addActionListener(actionEvent -> eventHandler.menuItemClick(actionEvent.getActionCommand()));
        menu.add(previousSlide);
        super.add(menu);
    }
}