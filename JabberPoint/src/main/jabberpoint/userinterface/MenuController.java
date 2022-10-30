package main.jabberpoint.userinterface;

import javax.swing.*;
import java.awt.*;

public class MenuController extends JMenuBar
{
    public MenuController(SwingEventHandler eventHandler)
    {
        JMenuBar mainMenuBar = new JMenuBar();

        JMenu application = new JMenu("Application");

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        exit.addActionListener(actionEvent -> eventHandler.menuItemClick("Exit"));
        application.add(exit);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        open.addActionListener(actionEvent -> eventHandler.menuItemClick("Open"));
        application.add(open);


        JMenu presentation = new JMenu("Slide");
        JMenuItem nextSlide = new JMenuItem("Next");
        nextSlide.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        nextSlide.addActionListener(actionEvent -> eventHandler.menuItemClick("Next slide"));
        presentation.add(nextSlide);

        JMenuItem previousSlide = new JMenuItem("Previous");
        previousSlide.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        previousSlide.addActionListener(actionEvent -> eventHandler.menuItemClick("Previous slide"));
        presentation.add(previousSlide);


        JMenu content = new JMenu("Content");
        JMenuItem nextContent = new JMenuItem("Next");
        nextContent.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        nextContent.addActionListener(actionEvent -> eventHandler.menuItemClick("Next content"));
        content.add(nextContent);

        JMenuItem previousContent = new JMenuItem("Previous");
        previousContent.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        previousContent.addActionListener(actionEvent -> eventHandler.menuItemClick("Previous content"));
        content.add(previousContent);

        JMenuItem remainingContent = new JMenuItem("Remaining");
        remainingContent.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        remainingContent.addActionListener(actionEvent -> eventHandler.menuItemClick("Remaining content"));
        content.add(remainingContent);

        JMenuItem removeAllContent = new JMenuItem("Clear");
        removeAllContent.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        removeAllContent.addActionListener(actionEvent -> eventHandler.menuItemClick("Remove content"));
        content.add(removeAllContent);


        JMenu help = new JMenu("Help");

        JMenuItem troubleShooting = new JMenuItem("Troubleshooting");
        troubleShooting.setAccelerator(KeyStroke.getKeyStroke('T', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        troubleShooting.addActionListener(actionEvent -> eventHandler.menuItemClick("Troubleshooting"));
        help.add(troubleShooting);
        help.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        about.addActionListener(actionEvent -> eventHandler.menuItemClick("About"));
        help.add(about);
        help.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        mainMenuBar.add(application);
        mainMenuBar.add(presentation);
        mainMenuBar.add(content);
        mainMenuBar.add(Box.createHorizontalGlue());
        mainMenuBar.add(help);

        super.add(mainMenuBar);
    }
}