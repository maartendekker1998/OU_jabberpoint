package main.jabberpoint.userinterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingEventHandler extends KeyAdapter implements EventHandler
{
    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                new NextSlideCommand().execute();
                break;
            case KeyEvent.VK_LEFT:
                new PreviousSlideCommand().execute();
                break;
        }
    }

    void menuItemClick(String actionCommand)
    {
        switch (actionCommand)
        {
            case "Next":
                new NextSlideCommand().execute();
                break;
            case "Previous":
                new PreviousSlideCommand().execute();
                break;
        }
    }
}
