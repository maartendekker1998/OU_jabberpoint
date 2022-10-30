package main.jabberpoint.userinterface;

import main.jabberpoint.domain.*;
import main.jabberpoint.domain.Image;

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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

public class SwingWindowHandler implements WindowHandler
{
    private static final String IMAGE_NOT_FOUND = "noimage.jpg";
    private final JFrame mainFrame = new JFrame();
    private final JPanel slide = new JPanel();

    private final int X_MARGIN = 50;
    private final int Y_MARGIN = 5;
    private int DEFAULT_WIDTH = 1000+(2*X_MARGIN);
    private int DEFAULT_HEIGHT = 720;
    private final int DEFAULT_FONT_SIZE = 36;
    private final String DEFAULT_FONT = "Helvetica";
    private final int DEFAULT_FONT_STYLE = Font.PLAIN;
    private static final Color DEFAULT_FONT_COLOR = Color.BLACK;
    private int DEFAULT_LABEL_WIDTH = DEFAULT_WIDTH-(2*X_MARGIN);

    private final Map<Component, Font> fontMap = new HashMap<>();
    private final Map<Component, SlideShowComponent> itemMap = new HashMap<>();
    private final Map<SlideShowComponent, BufferedImage> imageMap = new HashMap<>();
    private int previousComponentHeight = Y_MARGIN;
    private final SwingEventHandler eventHandler;

    public SwingWindowHandler(SwingEventHandler eventHandler)
    {
        this.eventHandler = eventHandler;
        this.slide.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent event)
            {
                Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
                previousComponentHeight = Y_MARGIN;
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
                    previousComponentHeight += component.getHeight();
                    isTitle = false;
                }
            }

            private boolean isImage(Component component)
            {
                return ((JLabel)component).getIcon() != null;
            }
        });
        this.mainFrame.setJMenuBar(new MenuController(this.eventHandler));
        this.mainFrame.addKeyListener(this.eventHandler);
        this.mainFrame.setContentPane(this.slide);
        this.mainFrame.setTitle(
                (Metadata.getInstance().metadata.get("showtitle") == null ? "JabberPoint" : Metadata.getInstance().metadata.get("showtitle")) + " by " +
                (Metadata.getInstance().metadata.get("presenter") == null ? "JabberPoint" : Metadata.getInstance().metadata.get("presenter")));
        this.mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.mainFrame.setIconImage(new ImageIcon("halloween.png").getImage());
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(null);
    }

    private float getScale(Rectangle area)
    {
        return Math.min(((float)area.width) / ((float)DEFAULT_WIDTH), ((float)area.height) / ((float)DEFAULT_HEIGHT));
    }

    @Override
    public void renderUI()
    {
        this.mainFrame.setVisible(true);
    }

    @Override
    public void addText(Text text)
    {
        JLabel label = new JLabel(text.getData());
        this.setStyles(text.getStyles(), label);
        label.setBounds(this.createBounds(this.calculateIndentation(text.getIndentation()), this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
        this.slide.repaint();
        this.itemMap.put(label, text);
    }

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
            showMessageDialog(this.mainFrame, "There is an error in the XML styling, refer to help\n for extra information.");
        }
        Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
        label.setFont(font.deriveFont(font.getStyle(), font.getSize() * this.getScale(area)));
        label.setText((styles.get("bullet") == null ? "" : styles.get("bullet") + " ") + (label.getText() == null ? "" : label.getText()));
        this.fontMap.put(label, font);
    }

    @Override
    public void addImage(Image image)
    {
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File(this.imageMap.containsKey(image) ? IMAGE_NOT_FOUND : image.getData()));
            Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
            java.awt.Image scaledImage = bufferedImage.getScaledInstance((int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)), java.awt.Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel( new ImageIcon(scaledImage));
            this.setStyles(image.getStyles(), imageLabel);
            imageLabel.setHorizontalTextPosition(SwingConstants.LEFT);
            imageLabel.setBounds(this.calculateIndentation(image.getIndentation()), this.previousComponentHeight,(int)(imageLabel.getText().length()*20* getScale(area))+(int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)));
            this.previousComponentHeight+=imageLabel.getHeight();
            this.slide.add(imageLabel);
            this.slide.repaint();
            imageLabel.getGraphics().drawImage(bufferedImage, 0, 0, (int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)), this.slide);
            this.imageMap.put(image, bufferedImage);
            this.itemMap.put(imageLabel, image);
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

    @Override
    public void setTitle(ConcreteSlide slide)
    {
        JLabel label = new JLabel(slide.getTitle());
        this.setStyles(slide.getStyles(), label);
        label.setBounds(this.createBounds(X_MARGIN, this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
        this.slide.repaint();
        this.itemMap.put(label, slide);
    }

    private int calculateIndentation(int indentation)
    {
        return X_MARGIN + (int)(X_MARGIN/2.6d*indentation);
    }

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

    @Override
    public void setTransitions(boolean transitions)
    {
        this.eventHandler.setTransitions(transitions);
    }

    @Override
    public void removeLastContent(Content content)
    {
        for (Content contentToRemove : ((ContentList)content).getContent())
        {
            Component componentToRemove = this.getKeyByValue(contentToRemove);
            if (componentToRemove == null) continue;
            this.previousComponentHeight-=componentToRemove.getHeight();
            this.slide.remove(componentToRemove);
            this.imageMap.remove(contentToRemove);
            this.itemMap.remove(componentToRemove);
        }
        this.slide.repaint();
    }

    private Component getKeyByValue(SlideShowComponent component)
    {
        for (Map.Entry<Component, SlideShowComponent> entry : this.itemMap.entrySet())
        {
            if (entry.getValue().equals(component)) return entry.getKey();
        }
        return null;
    }

    private Rectangle createBounds(int x, int y, int width, int height)
    {
        return new Rectangle(x, y, width-(2*X_MARGIN), height);
    }
}
