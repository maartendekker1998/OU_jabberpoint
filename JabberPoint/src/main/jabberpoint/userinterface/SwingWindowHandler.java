package main.jabberpoint.userinterface;

import main.jabberpoint.domain.Content;
import main.jabberpoint.domain.ContentList;
import main.jabberpoint.domain.Image;
import main.jabberpoint.domain.Text;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SwingWindowHandler implements WindowHandler
{
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
                    Font font = fontMap.get(component);
                    component.setFont(this.isImage(component) ? null : font.deriveFont(font.getSize() * getScale(area)));
                    if (this.isImage(component))
                    {
                        Icon icon = ((JLabel)component).getIcon();
                        BufferedImage bufferedImage = new BufferedImage((int)(component.getWidth() * getScale(area)), (int)(component.getHeight()* getScale(area)), BufferedImage.TYPE_INT_RGB);
                        Graphics2D graphics2D = bufferedImage.createGraphics();
                        graphics2D.drawImage(this.iconToImage(icon), 0, 0, (int)(component.getWidth() * getScale(area)), (int)(component.getHeight() * getScale(area)), null);
//                        JLabel image = new JLabel(new ImageIcon(bufferedImage));
////                        image.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 0), new EmptyBorder(0,0,0,0)));
//                        image.setBounds(0, 0, 80, 80);
//
//                        System.out.println(itemMap.get(component) == null);

                        ((JLabel) component).setIcon(new ImageIcon(this.iconToImage(icon)));
//                        Content c = itemMap.remove(component);
//                        component = image;

//                        itemMap.put(image, c);

//                        System.out.println(itemMap.get(component) == null);
//                        slide.remove(component);
//                        slide.add(image);
//                        itemMap.put(image, c);
                        slide.repaint();
//                        if (true) break;
//                        Rectangle w = new Rectangle(0, 0, ((JLabel)component).getIcon().getIconWidth(), ((JLabel)component).getIcon().getIconHeight());
//                        System.out.println(component.getSize().getHeight()*getScale(w));
//                        System.out.println(component.getSize().getWidth()*getScale(w));
//                        component.setSize((int)(component.getSize().getHeight()*getScale(w)), 10);
                    }
                    else component.setSize(component.getSize().width, component.getFont().getSize()+Y_MARGIN);
                    component.setBounds(createBounds(component.getX(), component.getY(), event.getComponent().getWidth(), component.getHeight()));
                    if (!isTitle) component.setLocation(calculateIndentation(itemMap.get(component).getIndentation()), previousComponentHeight);
                    previousComponentHeight += component.getHeight();
                    isTitle = false;
                }
            }

            private boolean isImage(Component component)
            {
                return ((JLabel)component).getIcon() != null;
            }

            private java.awt.Image iconToImage(Icon icon)
            {
                BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(icon.getIconWidth(), icon.getIconHeight());
                Graphics2D graphics2D = image.createGraphics();
                icon.paintIcon(null, graphics2D, 0, 0);
                graphics2D.dispose();
                return image;
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
//        JLabel label = new JLabel(/*this.createIndentation(text.getIndentation()) + */text.getData());

//        System.out.println(text.getData());
//        System.out.println(text.getStyles());
        JLabel label = new JLabel(text.getData());

        Font font = this.defaultFont;
//        font = new Font(text.getStyles().get("font") == null ? "Helvetica" : text.getStyles().get("font"), Font.BOLD, text.getStyles().get("fontsize") == null ? DEFAULT_LABEL_HEIGHT : Integer.parseInt(text.getStyles().get("fontsize")));
        Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
        label.setFont(label.getFont().deriveFont(font.getSize() * this.getScale(area)));
        label.setBounds(this.createBounds(this.calculateIndentation(text.getIndentation()), this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
//        label.setBorder(new CompoundBorder(new LineBorder(Color.BLACK,1), new EmptyBorder(-7,0,0,0)));
        this.fontMap.put(label, font);
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
//        if (this.previousComponentHeight+40 > DEFAULT_HEIGHT)
//        {
//            System.out.println("tsetse");
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
            java.awt.Image myPicture = ImageIO.read(new File(image.getData()));
            java.awt.Image newImage = myPicture.getScaledInstance(54, DEFAULT_LABEL_HEIGHT, java.awt.Image.SCALE_DEFAULT);

//            BufferedImage myPicture = ImageIO.read(new File(image.getData()));
//            BufferedImage output = new BufferedImage(54, DEFAULT_LABEL_HEIGHT, myPicture.getType());
////            BufferedImage newImage = myPicture.getScaledInstance(54, DEFAULT_LABEL_HEIGHT, java.awt.Image.SCALE_DEFAULT);
//
//            Graphics2D graphics2D = output.createGraphics();
//            graphics2D.drawImage(myPicture, this.calculateIndentation(image.getIndentation()), this.previousComponentHeight, 54, DEFAULT_LABEL_HEIGHT, null);
//            graphics2D.dispose();



            JLabel icon = new JLabel(new ImageIcon(newImage));
            icon.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(0,0,0,0)));
            icon.setBounds(this.calculateIndentation(image.getIndentation()), this.previousComponentHeight,54, DEFAULT_LABEL_HEIGHT);
            this.previousComponentHeight+=icon.getHeight();
            this.slide.add(icon);
            this.slide.repaint();
            this.itemMap.put(icon, image);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

//    private String createIndentation(int indentation)
//    {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < indentation; i++) stringBuilder.append("  ");
//        return stringBuilder.toString();
//    }

    private int calculateIndentation(int indentation)
    {
        return X_MARGIN + (int)(X_MARGIN/2.6d*indentation);
    }

    @Override
    public void clear()
    {
        this.slide.removeAll();
        this.itemMap.clear();
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
        Component component = this.getValueByKey(((ContentList)content).getContent().get(0));


        if (component == null) return;
        this.previousComponentHeight-=component.getHeight();
        this.slide.remove(component);
        this.itemMap.remove(component);
        this.slide.repaint();
    }

    private Component getValueByKey(Content component)
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
