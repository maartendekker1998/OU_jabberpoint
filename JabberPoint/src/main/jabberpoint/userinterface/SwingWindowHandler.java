package main.jabberpoint.userinterface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

public class SwingWindowHandler implements WindowHandler
{
    private final JFrame mainFrame = new JFrame();
    private final JPanel slide = new JPanel();

    private final int DEFAULT_WIDTH = 400;
    private final int DEFAULT_HEIGHT = 800;
    private final int X_MARGIN = 5;
    private final int Y_MARGIN = 5;
    private final int DEFAULT_LABEL_HEIGHT = 36;
    private final int DEFAULT_LABEL_WIDTH = DEFAULT_WIDTH-(2*X_MARGIN);

    private final Map<Component, Font> fontMap = new HashMap<>();
    private final Font defaultFont = new Font("Helvetica", Font.BOLD, DEFAULT_LABEL_HEIGHT);
    private int previousComponentHeight = 5;

    public SwingWindowHandler()
    {
        this.slide.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
                int previousComponentHeight = 5;
                for (Component component : mainFrame.getContentPane().getComponents())
                {
                    Font font = fontMap.get(component);
                    component.setFont(font.deriveFont(font.getSize() * getScale(area)));
                    component.setBounds(createBounds(component.getX(),component.getY(),e.getComponent().getWidth(), component.getHeight()));
                    component.setLocation(5, previousComponentHeight);
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
    public void addNode(int indentation, String text)
    {
        JLabel label = new JLabel(text);
        Font font = this.defaultFont;
        Rectangle area = new Rectangle(0, 0, slide.getWidth(), slide.getHeight());
        label.setFont(font.deriveFont(font.getSize() * getScale(area)));
        label.setBounds(this.createBounds(X_MARGIN + indentation, this.previousComponentHeight, DEFAULT_LABEL_WIDTH-X_MARGIN, DEFAULT_LABEL_HEIGHT));

//        label.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0,0,0,0)));//temp

//        JLabel label2 = new JLabel("Some data");
//        label2.setFont(new Font("Helvetica", Font.BOLD,8));
//
//        label2.setBorder(new CompoundBorder(new LineBorder(Color.RED), margin));//temp
//
//        label2.setBounds(this.createBounds(indentation, DEFAULT_LABEL_HEIGHT, DEFAULT_LABEL_WIDTH-15, 20));
//
//        JLabel label3 = new JLabel("other data");
//        label3.setFont(new Font("Helvetica", Font.BOLD,DEFAULT_LABEL_HEIGHT));
//
//        label3.setBorder(new CompoundBorder(new LineBorder(Color.BLUE), margin));//temp
//
//        label3.setBounds(this.createBounds(indentation,DEFAULT_LABEL_HEIGHT+20, DEFAULT_LABEL_WIDTH-15, DEFAULT_LABEL_HEIGHT));
        this.fontMap.put(label, font);
//        this.fontMap.put(label2, label2.getFont());
//        this.fontMap.put(label3, label3.getFont());
        this.slide.add(label);
//        this.slide.add(label2);
//        this.slide.add(label3);
        this.previousComponentHeight+=label.getHeight();
        this.mainFrame.setSize(label.getWidth() > DEFAULT_WIDTH ? label.getWidth() + X_MARGIN : DEFAULT_WIDTH, (40+this.previousComponentHeight + Y_MARGIN));

        this.slide.repaint();
    }

    private Rectangle createBounds(int x, int y, int width, int height)
    {
        return new Rectangle(x, y, width-10, height);
    }
}
