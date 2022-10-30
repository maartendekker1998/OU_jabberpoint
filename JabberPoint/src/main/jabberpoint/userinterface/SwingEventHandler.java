package main.jabberpoint.userinterface;

import main.jabberpoint.domain.Metadata;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

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
            case KeyEvent.VK_END:
                if (this.transitions) new RemainingContentCommand().execute();
                break;
            case KeyEvent.VK_HOME:
                if (this.transitions) new RemoveAllContentCommand().execute();
                break;
            case KeyEvent.VK_Q:
                System.exit(0);
                break;
        }
    }

    void menuItemClick(String actionCommand)
    {
        switch (actionCommand)
        {
            case "Next slide":
                new NextSlideCommand().execute();
                break;
            case "Previous slide":
                new PreviousSlideCommand().execute();
                break;
            case "Next content":
                new NextContentCommand().execute();
                break;
            case "Previous content":
                new RemoveLastContentCommand().execute();
                break;
            case "Remaining content":
                new RemainingContentCommand().execute();
                break;
            case "Remove content":
                new RemoveAllContentCommand().execute();
                break;
            case "About":
                JOptionPane.showMessageDialog(null,
                        "This presentation is made by " + Metadata.getInstance().metadata.get("presenter")
                                 + " for the Design for\nchange course of the Open Universiteit The Netherlands.",
                        "About", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Open":
                String filename = JOptionPane.showInputDialog("Enter the filename");

                if (filename != null && new File(filename).exists()){
                    new OpenFileCommand(filename).execute();
                    break;
                }
                JOptionPane.showMessageDialog(null,
                        "The file you are trying to open does not exist.",
                        "Error opening file", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
