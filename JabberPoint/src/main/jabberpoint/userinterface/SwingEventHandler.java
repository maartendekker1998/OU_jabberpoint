package main.jabberpoint.userinterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingEventHandler extends KeyAdapter implements EventHandler
{
    private boolean transitions = false;

    void setTransitions(boolean transitions)
    {
        this.transitions = transitions;
    }

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
            case KeyEvent.VK_DOWN:
                if (this.transitions) new NextContentCommand().execute();
                break;
            case KeyEvent.VK_UP:
                if (this.transitions) new RemoveLastContentCommand().execute();
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
