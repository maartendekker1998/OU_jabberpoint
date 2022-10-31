package main.jabberpoint.userinterface;

import javax.swing.*;
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
                if (this.transitions) new NextContentCommand().execute();
                break;
            case "Previous content":
                if (this.transitions) new RemoveLastContentCommand().execute();
                break;
            case "Remaining content":
                if (this.transitions) new RemainingContentCommand().execute();
                break;
            case "Remove content":
                if (this.transitions) new RemoveAllContentCommand().execute();
                break;
            case "Troubleshooting":
                JOptionPane.showMessageDialog(null,
                        "XML:\n" +
                                 "JabberPoint uses XML files as source of the presentations. There are various keywords that can be used:\n" +
                                 "   <metadata></metadata> is extra information on the slideshow;\n" +
                                 "Properties that can be used on metadata are:\n" +
                                 "    - \"presenter\", this is the name of the presenter of the presentation;\n" +
                                 "    - \"showtitle\", this is the title of the presentation.\n" +
                                 "   <slide></slide> is a new slide and can contain content such as text items, bullet lists and images, it should be at\n" +
                                 "      the same XML level as metadata;\n" +
                                 "Properties that can be used on a slide are:\n" +
                                 "    - \"transitions\", this can be set to a value of \"true\" or \"false\"  to set the presentation to show content\n" +
                                 "           one by one in stead of a full slide at once.\n" +
                                 "   <item></item> may contain data to show on a slide, these are child's of slide items;\n" +
                                 "Properties that can be used on items are:\n" +
                                 "    - \"kind\", this is the type of content, this can be \"text\", \"image\" or \"bulletlist\" (required);\n" +
                                 "    - \"level\", this sets the indentation of the content, this must be a positive number (required);\n" +
                                 "    - \"font\", sets the font, this must be installed on the computer (optional);\n" +
                                 "    - \"fontsize\", this will be set to the size of the characters, this must be a number (optional);\n" +
                                 "    - \"color\", sets the color of the characters, this must be presented as hex, example: (#FG65A3) (optional);\n" +
                                 "    - \"bullet\" (only on kind bulletlist), this can be set to a character that will be shown in front of content (optional).\n" +
                                 "\n" +
                                 "Bulletlists can be stacked as far as you like to, it is recommended to set the indentation of the bulletlist, the same as\n" +
                                 "   the content it contains.\n" +
                                 "If a value is not provided as described above, an error message is raised and a default value will be used.\n" +
                                 "Image and bulletlist ignores the font, fontsize and color properties.\n" +
                                 "If an image cannot be found, an error message is raised and a default image (indicating cannot find image) will be used.\n" +
                                 "The location of an image can either be provided as a full path to an image, or just the name of the image if it can be\n" +
                                 "found in the root directory where the JabberPoint is located. It is recommended to use a full path.\n" +
                                 "\n" +
                                 "Keys:\n" +
                                 "The short keys that can be used are as following\n" +
                                 "    - Q, exits JabberPoint;\n" +
                                 "    - O, opens an file selection for opening a new presentation file;\n" +
                                 "    - W, removes last added content (only when slide has transitions);\n" +
                                 "    - S, adds next content (only when slide has transitions);\n" +
                                 "    - A, go to previous slide;\n" +
                                 "    - D, go to next slide;\n" +
                                 "    - X, adds remaining content (only when slide has transitions);\n" +
                                 "    - C, clears all content (only when slide has transitions);\n" +
                                 "    - Z, shows information about JabberPoint;\n" +
                                 "    - T, shows troubleshooting help.\n",
                        "Troubleshooting", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "About":
                JOptionPane.showMessageDialog(null,
                        "JabberPoint is made by Bart Janssen and Maarten Dekker as an assignment\nfor the 'Design for change' course of the Open Universiteit The Netherlands.",
                        "About", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Open":
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    new OpenFileCommand(fileChooser.getSelectedFile().getAbsolutePath()).execute();
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
