package main.jabberpoint.userinterface;

import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.ContentList;
import main.jabberpoint.domain.Image;
import main.jabberpoint.domain.Text;
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
    private int DEFAULT_WIDTH = 720+(2*X_MARGIN);
    private int DEFAULT_HEIGHT = 1012;
    private final int DEFAULT_LABEL_HEIGHT = 36;
    private int DEFAULT_LABEL_WIDTH = DEFAULT_WIDTH-(2*X_MARGIN);

    private final Map<Component, Font> fontMap = new HashMap<>();
    private final Map<Component, Content> itemMap = new HashMap<>();
    private final Map<Content, BufferedImage> imageMap = new HashMap<>();
    private final Font defaultFont = new Font("Helvetica", Font.BOLD, DEFAULT_LABEL_HEIGHT);
    private int previousComponentHeight = 5;
    private final SwingEventHandler eventHandler;

    public SwingWindowHandler(SwingEventHandler eventHandler)
    {
        this.eventHandler = eventHandler;
        this.slide.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent event)
            {
                DEFAULT_LABEL_WIDTH = (slide.getWidth()+16)-(2*X_MARGIN);
                Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
                previousComponentHeight = Y_MARGIN;
                boolean isTitle = true;
                for (Component component : mainFrame.getContentPane().getComponents())
                {
                    if (this.isImage(component))
                    {
                        BufferedImage bufferedImage = imageMap.get(itemMap.get(component));
                        java.awt.Image image = bufferedImage.getScaledInstance((int)(bufferedImage.getWidth()*getScale(area)), (int)(bufferedImage.getHeight()*getScale(area)), java.awt.Image.SCALE_SMOOTH);
                        ((JLabel)component).setIcon(new ImageIcon(image));
                        component.setSize((int)(bufferedImage.getWidth()*getScale(area)), (int)(bufferedImage.getHeight()*getScale(area)));
                    }
                    else
                    {
                        Font font = fontMap.get(component);
                        component.setFont(this.isImage(component) ? null : font.deriveFont(font.getSize() * getScale(area)));
                        component.setSize(component.getSize().width, component.getFont().getSize()+Y_MARGIN);
                        component.setBounds(createBounds(component.getX(), component.getY(), event.getComponent().getWidth(), component.getHeight()));
                    }
                    if (!isTitle) component.setLocation(calculateIndentation(itemMap.get(component).getIndentation()), previousComponentHeight);
                    previousComponentHeight += component.getHeight();
                    isTitle = false;
                }
            }

            private boolean isImage(Component component)
            {
                return ((JLabel)component).getIcon() != null;
            }
        });
        this.mainFrame.setMenuBar(new MenuController(this.eventHandler));
        this.mainFrame.addKeyListener(this.eventHandler);
        this.mainFrame.setContentPane(this.slide);
        this.mainFrame.setTitle("JabberPoint");
        this.mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
        Font font = this.defaultFont;
        Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
        label.setFont(label.getFont().deriveFont(font.getSize() * this.getScale(area)));
        label.setBounds(this.createBounds(this.calculateIndentation(text.getIndentation()), this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
//        label.setBorder(new CompoundBorder(new LineBorder(Color.BLACK,1), new EmptyBorder(-7,0,0,0)));
        this.fontMap.put(label, font);
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
//        if (this.previousComponentHeight+40 > DEFAULT_HEIGHT)
//        {
//            System.out.println("test");
//        }
//        this.mainFrame.setSize(this.previousComponentHeight > 400 ? (DEFAULT_HEIGHT=previousComponentHeight) : DEFAULT_HEIGHT, DEFAULT_WIDTH);
//        this.mainFrame.setSize(this.previousComponentHeight < 400 ? DEFAULT_HEIGHT : (DEFAULT_HEIGHT=previousComponentHeight), DEFAULT_HEIGHT);
        this.slide.repaint();
        this.itemMap.put(label, text);
    }

    @Override
    public void addImage(Image image)
    {
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File(this.imageMap.containsKey(image) ? IMAGE_NOT_FOUND : image.getData()));
            Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
            java.awt.Image scaledImage = bufferedImage.getScaledInstance((int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)), java.awt.Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageLabel.setBounds(this.calculateIndentation(image.getIndentation()), this.previousComponentHeight, (int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)));
//            icon.setBorder(new CompoundBorder(new LineBorder(Color.BLACK,1), new EmptyBorder(0,0,0,0)));
            this.previousComponentHeight+=imageLabel.getHeight();
            this.slide.add(imageLabel);
            this.slide.repaint();
            imageLabel.getGraphics().drawImage(bufferedImage, 0, 0, (int)(bufferedImage.getWidth()*this.getScale(area)), (int)(bufferedImage.getHeight()*this.getScale(area)), this.slide);
            this.imageMap.put(image, bufferedImage);
            this.itemMap.put(imageLabel, image);
        }
        catch (IOException e)
        {
            if (this.imageMap.containsKey(image))
            {
                showMessageDialog(this.mainFrame, "Serious image error.");
                return;
            }
            this.imageMap.put(image, null);
            this.addImage(image);
        }
    }

    @Override
    public void setTitle(String title)
    {
        JLabel label = new JLabel(title);
        Font font = this.defaultFont;
        Rectangle area = new Rectangle(0, 0, this.slide.getWidth(), this.slide.getHeight());
        label.setFont(font.deriveFont(font.getSize() * this.getScale(area)));
        label.setBounds(this.createBounds(X_MARGIN, this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
        this.fontMap.put(label, font);
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
        this.slide.repaint();
    }

    private int calculateIndentation(int indentation)
    {
        return X_MARGIN + (int)(X_MARGIN/2.6d*indentation);
    }

    @Override
    public void clear()
    {
        this.slide.removeAll();
        this.itemMap.clear();
        this.imageMap.clear();
        this.previousComponentHeight = 5;
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

    private Component getKeyByValue(Content component)
    {
        for (Map.Entry<Component, Content> entry : this.itemMap.entrySet())
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
