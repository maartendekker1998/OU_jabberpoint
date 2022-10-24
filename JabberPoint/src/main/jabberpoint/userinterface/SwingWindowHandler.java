package main.jabberpoint.userinterface;

import main.jabberpoint.domain.Text;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

public class SwingWindowHandler implements WindowHandler
{
    private final JFrame mainFrame = new JFrame();
    private final JPanel slide = new JPanel();

    private final int X_MARGIN = 50;
    private final int Y_MARGIN = 5;
    private int DEFAULT_WIDTH = 720+(2*X_MARGIN);
    private int DEFAULT_HEIGHT = 512;
    private final int DEFAULT_LABEL_HEIGHT = 36;
    private int DEFAULT_LABEL_WIDTH = DEFAULT_WIDTH-(2*X_MARGIN);

    private final Map<Component, Font> fontMap = new HashMap<>();
    private final Font defaultFont = new Font("Helvetica", Font.BOLD, DEFAULT_LABEL_HEIGHT);
    private int previousComponentHeight = 5;

    public SwingWindowHandler(SwingEventHandler eventHandler)
    {
        this.slide.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                DEFAULT_LABEL_WIDTH = (slide.getWidth()+16)-(2*X_MARGIN);
                Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
                int previousComponentHeight = Y_MARGIN;
                for (Component component : mainFrame.getContentPane().getComponents())
                {
                    Font font = fontMap.get(component);
                    component.setFont(font.deriveFont(font.getSize() * getScale(area)));
                    component.setSize(component.getSize().width, component.getFont().getSize()+Y_MARGIN);
                    component.setBounds(createBounds(component.getX(),component.getY(),e.getComponent().getWidth(), component.getHeight()));
                    component.setLocation(X_MARGIN, previousComponentHeight);
                    previousComponentHeight += component.getHeight();
                }
            }
        });
//        this.nextButton.addActionListener(event ->
//        {
////            evenHandler.handle(new NextSlideCommand());
//            //Not needed in this case
////            SwingUtilities.invokeLater(this::nextSlide);
//        });
//        menuItem.addActionListener(event ->
//        {
//            evenHandler.handle(new NextSlideCommand());
//            //Not needed in this case
//            SwingUtilities.invokeLater(this::nextSlide);
//        });
        this.mainFrame.setMenuBar(new MenuController(eventHandler));
        this.mainFrame.addKeyListener(eventHandler);
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
    public void addNode(Text text)
    {
        JLabel label = new JLabel(this.createIndentation(text.getIndentation()) + text.getData());
        Font font = this.defaultFont;
        Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
        label.setFont(font.deriveFont(font.getSize() * this.getScale(area)));
        label.setBounds(this.createBounds(X_MARGIN + (text.getIndentation()*20), this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
        this.fontMap.put(label, font);
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
        System.out.println(DEFAULT_WIDTH);
        System.out.println(slide.getWidth()+16);
//        if (this.previousComponentHeight+40 > DEFAULT_HEIGHT)
//        {
//            System.out.println("tsetse");
//        }
//        this.mainFrame.setSize(this.previousComponentHeight > 400 ? (DEFAULT_HEIGHT=previousComponentHeight) : DEFAULT_HEIGHT, DEFAULT_WIDTH);
//        this.mainFrame.setSize(this.previousComponentHeight < 400 ? DEFAULT_HEIGHT : (DEFAULT_HEIGHT=previousComponentHeight), DEFAULT_HEIGHT);
        this.slide.repaint();
    }

    @Override
    public void setTitle(String title)
    {
        JLabel label = new JLabel(title);
        Font font = this.defaultFont;
        Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
        label.setFont(font.deriveFont(font.getSize() * this.getScale(area)));
        label.setBounds(this.createBounds(X_MARGIN, this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, label.getFont().getSize()+Y_MARGIN));
        label.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new EmptyBorder(-7,0,0,0)));
        this.fontMap.put(label, font);
        this.slide.add(label);
        this.previousComponentHeight+=label.getHeight();
        this.slide.repaint();
    }

    private String createIndentation(int indentation)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indentation; i++) stringBuilder.append("  ");
        return stringBuilder.toString();
    }

    @Override
    public void clear()
    {
        this.slide.removeAll();
        this.previousComponentHeight = 5;
    }

    private Rectangle createBounds(int x, int y, int width, int height)
    {
        return new Rectangle(x, y, width-(2*X_MARGIN), height);
    }
}
