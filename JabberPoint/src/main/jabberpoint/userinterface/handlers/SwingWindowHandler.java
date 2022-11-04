package main.jabberpoint.userinterface.handlers;

import main.jabberpoint.domain.*;
import main.jabberpoint.domain.components.*;
import main.jabberpoint.domain.components.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Part of Abstract Factory Pattern
 * Role: Concrete class that will be created by the HandleFactory
 * @see HandlerFactory
 * This class is responsible for rendering the actual UI items on the window
 */
public class SwingWindowHandler implements WindowHandler
{
    /**
     * UI related variables, this conrains the main frame which is shown and default values
     */
    private static final String IMAGE_NOT_FOUND = "noimage.jpg";
    private static final String DEFAULT_JABBER_POINT_TITLE = "JabberPoint";
    private final JFrame mainFrame = new JFrame();
    private final JPanel slide = new JPanel();
    private final int X_MARGIN = 50;
    private final int Y_MARGIN = 5;
    private int DEFAULT_WIDTH = 1000+(2*X_MARGIN);
    private int DEFAULT_HEIGHT = 800;
    private final int DEFAULT_FONT_SIZE = 36;
    private final String DEFAULT_FONT = "Arial";
    private final int DEFAULT_FONT_STYLE = Font.PLAIN;
    private final int TITLE_WHITESPACE = 20;
    private final int IMAGE_BOTTOM_MARGIN = 5;
    private static final Color DEFAULT_FONT_COLOR = Color.BLACK;
    private final Map<Component, Font> fontMap = new HashMap<>();
    private final Map<Component, SlideShowComponent> itemMap = new HashMap<>();
    private final Map<SlideShowComponent, BufferedImage> imageMap = new HashMap<>();
    private int previousComponentHeight = Y_MARGIN;
    private final SwingEventHandler eventHandler;

    /**
     * Prepares the UI, this also contains the functionality to be scalable
     * @param menuHandler an menu handler used to handle key presses and menu clicks
     */
    SwingWindowHandler(SwingMenuHandler menuHandler)
    {
        this.eventHandler = menuHandler.getEventHandler();
        this.slide.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent event)
            {
                if (itemMap.isEmpty()) return;
                Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
                previousComponentHeight = Y_MARGIN+TITLE_WHITESPACE;
                boolean isTitle = true;
                for (Component component : mainFrame.getContentPane().getComponents())
                {
                    if (this.isImage(component))
                    {
                        Font font = fontMap.get(component);
                        BufferedImage bufferedImage = imageMap.get(itemMap.get(component));
                        java.awt.Image image = bufferedImage.getScaledInstance((int)(bufferedImage.getWidth()*getScale(area)), (int)(bufferedImage.getHeight()*getScale(area)), java.awt.Image.SCALE_SMOOTH);
                        ((JLabel)component).setIcon(new ImageIcon(image));
                        component.setFont(font.deriveFont(font.getStyle(), font.getSize() * getScale(area)));
                        component.setSize((int)(bufferedImage.getWidth()*getScale(area))+(int)(((JLabel)component).getText().length()*20* getScale(area)), (int)(bufferedImage.getHeight()*getScale(area)));
                    }
                    else
                    {
                        Font font = fontMap.get(component);
                        component.setFont(font.deriveFont(font.getStyle(), font.getSize() * getScale(area)));
                        component.setSize(component.getSize().width, component.getFont().getSize()+Y_MARGIN);
                        component.setBounds(createBounds(component.getX(), component.getY(), event.getComponent().getWidth(), component.getHeight()));
                    }
                    if (!isTitle) component.setLocation(calculateIndentation(((Content)itemMap.get(component)).getIndentation()), previousComponentHeight);
                    previousComponentHeight += component.getHeight() + (this.isImage(component) ? IMAGE_BOTTOM_MARGIN : 0);
                    isTitle = false;
                }
            }

            private boolean isImage(Component component)
            {
                return ((JLabel)component).getIcon() != null;
            }
        });
        this.mainFrame.setJMenuBar(menuHandler);
        this.mainFrame.addKeyListener(this.eventHandler);
        this.mainFrame.setContentPane(this.slide);
        this.mainFrame.setTitle(DEFAULT_JABBER_POINT_TITLE);
        this.mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.mainFrame.setIconImage(this.getImageIcon("halloween.png"));
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(null);
    }

    /**
     * Sets the eventHandler information if the slide contains transitions or not
     * @param transitions transitions of the slide
     * @see EventHandler
     */
    @Override
    public void setTransitions(boolean transitions)
    {
        this.eventHandler.setTransitions(transitions);
    }

    /**
     * Sets the window visible, the window is prepared in the constructor
     */
    @Override
    public void renderUserInterface()
    {
        this.mainFrame.setVisible(true);
    }

    /**
     * Sets the title of the slide, this is always executed whether the slide has transitions or not
     * @param slide a concrete slide type content
     */
    @Override
    public void setTitle(ConcreteSlide slide)
    {
        if (slide.getMetadata().get("showtitle") == null && slide.getMetadata().get("presenter") == null) this.mainFrame.setTitle(DEFAULT_JABBER_POINT_TITLE);
        else this.mainFrame.setTitle(
            (slide.getMetadata().get("showtitle") == null ? "JabberPoint" : Metadata.getInstance().metadata.get("showtitle")) + " by " +
            (slide.getMetadata().get("presenter") == null ? "JabberPoint" : Metadata.getInstance().metadata.get("presenter")));
        JLabel label = new JLabel(slide.getTitle());
        this.setStyles(slide.getStyles(), label);
        label.setBounds(this.createBounds(X_MARGIN, (this.previousComponentHeight), this.slide.getWidth()-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        this.slide.add(label);
        this.previousComponentHeight+=(label.getHeight()+TITLE_WHITESPACE);
        this.slide.repaint();
        SwingUtilities.invokeLater(() -> this.itemMap.put(label, slide));
    }

    /**
     * Adds a text type content to the slide, this will create a label with styles initiated
     * It also sets the bounds and location of the label
     * @param text a text type content
     */
    @Override
    public void addText(Text text)
    {
        JLabel label = new JLabel(text.getData());
        this.setStyles(text.getStyles(), label);
        label.setBounds(this.createBounds(this.calculateIndentation(text.getIndentation()), this.previousComponentHeight, this.slide.getWidth()-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
        this.slide.repaint();
        SwingUtilities.invokeLater(() -> this.itemMap.put(label, text));
    }

    /**
     * Adds an image type content to the slide, this will create a label with styles initiated
     * An image is loaded in the label, it is also possible that the label contains text and an image
     * this is so that the BulletList styles can be set on images as well
     * It also sets the bounds and location of the label
     * If the image provided cannot be found, the user will be notified with a message and a default image will be used instead
     * @param image an image type content
     * @see BulletList
     */
    @Override
    public void addImage(Image image)
    {
        try
        {
            BufferedImage bufferedImage = this.imageMap.containsKey(image) ? this.getImageIcon(IMAGE_NOT_FOUND) : ImageIO.read(new File(image.getData()));
            if (bufferedImage == null) throw new IOException();
            Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
            java.awt.Image scaledImage = bufferedImage.getScaledInstance((int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)), java.awt.Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            this.setStyles(image.getStyles(), imageLabel);
            imageLabel.setHorizontalTextPosition(SwingConstants.LEFT);
            imageLabel.setBounds(this.calculateIndentation(image.getIndentation()), this.previousComponentHeight,(int)(imageLabel.getText().length()*20* getScale(area))+(int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)));
            this.previousComponentHeight+=(imageLabel.getHeight()+IMAGE_BOTTOM_MARGIN);
            this.slide.add(imageLabel);
            this.slide.repaint();
            imageLabel.getGraphics().drawImage(bufferedImage, 0, 0, (int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)), this.slide);
            this.imageMap.put(image, bufferedImage);
            SwingUtilities.invokeLater(() -> this.itemMap.put(imageLabel, image));
        }
        catch (Exception e)
        {
            if (this.imageMap.containsKey(image))
            {
                showMessageDialog(this.mainFrame, "There has occurred an error.");
                return;
            }
            this.imageMap.put(image, null);
            this.addImage(image);
            showMessageDialog(this.mainFrame, "The image \"" + image.getData() + "\" cannot be found, refer to\n help for extra information.");
        }
    }

    /**
     * Removes the last added content from the window
     * This gets called recursively if the content is type ContentComposite to remove all its children also
     * @param content Content item to remove
     */
    @Override
    public void removeLastContent(List<? extends SlideShowComponent> content)
    {
        for (SlideShowComponent contentToRemove : content)
        {
            if (contentToRemove instanceof ContentComposite)
            {
                this.removeLastContent(((ContentComposite)contentToRemove).getData());
                continue;
            }
            Component componentToRemove = this.getKeyByValue(contentToRemove);
            if (contentToRemove instanceof Image) this.previousComponentHeight-=IMAGE_BOTTOM_MARGIN;
            if (componentToRemove == null) continue;
            this.previousComponentHeight-=componentToRemove.getHeight();
            this.slide.remove(componentToRemove);
            this.imageMap.remove(contentToRemove);
            this.itemMap.remove(componentToRemove);
        }
        this.slide.repaint();
    }

    /**
     * Clears the slide, this can be provided information whether it should remove of preserve the title
     * @param clearTitle boolean to determine if it should remove the title as well
     */
    @Override
    public void clear(boolean clearTitle)
    {
        ConcreteSlide title = null;
        if (!clearTitle) title = (ConcreteSlide)this.itemMap.get(this.slide.getComponents()[0]);
        this.slide.removeAll();
        this.previousComponentHeight = Y_MARGIN;
        this.itemMap.clear();
        this.fontMap.clear();
        this.imageMap.clear();
        if (!clearTitle) this.setTitle(title);
        this.slide.repaint();
    }

    /**
     * Sets styles which are optionally provided on a Content type
     * It will use default values if the styles parameter is empty or if certain styles are not provided
     * If one of the styles contains an error, it will notify the user with this information and fallback to a default style
     * @param styles the map containing styles of an Content item
     * @param label the label to set the styles to
     * @see SlideShowComponent
     */
    private void setStyles(Map<String, String> styles, JLabel label)
    {
        Font font = null;
        try
        {
            font = new Font(
                    (styles.get("font") == null ? DEFAULT_FONT : styles.get("font")),
                    (DEFAULT_FONT_STYLE),
                    (styles.get("fontsize") == null ? DEFAULT_FONT_SIZE : Integer.parseInt(styles.get("fontsize"))));
            label.setForeground(styles.get("color") == null ? DEFAULT_FONT_COLOR : Color.decode(styles.get("color")));
        }
        catch (NumberFormatException e)
        {
            if (font == null) font = new Font(
                    (styles.get("font") == null ? DEFAULT_FONT : styles.get("font")),
                    (DEFAULT_FONT_STYLE),
                    (DEFAULT_FONT_SIZE));
            label.setForeground(DEFAULT_FONT_COLOR);
            showMessageDialog(this.mainFrame, "There is an error in the XML styling, refer to help → troubleshooting\n for extra information.");
        }
        Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
        label.setFont(font.deriveFont(font.getStyle(), font.getSize() * this.getScale(area)));
        label.setText((styles.get("bullet") == null ? "" : styles.get("bullet") + " ") + (label.getText() == null ? "" : label.getText()));
        this.fontMap.put(label, font);
    }

    /**
     * Loads an image from the resource folder
     * @param image the name of an image, example: 'text.png'
     * @return an instance of a BufferedImage, or null if the image does not exist
     */
    private BufferedImage getImageIcon(String image)
    {
        try
        {
            InputStream inputStream = super.getClass().getClassLoader().getResourceAsStream(image);
            if (inputStream == null) return null;
            return ImageIO.read(inputStream);
        }
        catch (IOException e)
        {
            return null;
        }
    }

    /**
     * Calculates a scale that can be used to scale content with the scaling of the window
     * @param area this contains the width and height of the current window
     * @return a float type number that is the factor of the scaling
     */
    private float getScale(Rectangle area)
    {
        return Math.min(((float)area.width) / ((float)DEFAULT_WIDTH), ((float)area.height) / ((float)DEFAULT_HEIGHT));
    }

    /**
     * Calculates the X coordinate to determine where the UI item should be rendered at
     * @param indentation the indentation as provided in the Content
     * @return x coordinate
     * @see Content
     */
    private int calculateIndentation(int indentation)
    {
        return X_MARGIN + (int)(X_MARGIN/2.6d*indentation);
    }

    /**
     * Extracts the key of a map by the use of a value from the itemMap
     * @param component a component to find a key
     * @return the key of the corresponding component value
     */
    private Component getKeyByValue(SlideShowComponent component)
    {
        for (Map.Entry<Component, SlideShowComponent> entry : this.itemMap.entrySet())
        {
            if (entry.getValue().equals(component)) return entry.getKey();
        }
        return null;
    }

    /**
     * Creates bounds for an UI item, mainly used to make sure the UI item size will not exceed the window size
     * @param x x coordinate of the UI item
     * @param y y coordinate of the UI item
     * @param width width of the UI item
     * @param height height of the UI item
     * @return a rectangle containing the bounds for the UI item
     */
    private Rectangle createBounds(int x, int y, int width, int height)
    {
        return new Rectangle(x, y, width-(2*X_MARGIN), height);
    }
}
