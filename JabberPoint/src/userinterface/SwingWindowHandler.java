package userinterface;

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

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int X_MARGIN = 5;
    private final int Y_MARGIN = 5;
    private final int DEFAULT_LABEL_HEIGHT = 36;
    private final int DEFAULT_LABEL_WIDTH = WIDTH-(2*X_MARGIN);

    private Map<Component, Integer> labelList = new HashMap<>();

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
                    Font font = new Font(component.getFont().getName(), component.getFont().getStyle(), labelList.get(component));
                    component.setFont(font.deriveFont(font.getSize() * getScale(area)));
                    component.setSize(component.getSize().width, component.getFont().getSize());
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
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(null);
    }

    private float getScale(Rectangle area)
    {
        return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
    }

    @Override
    public void renderUI()
    {
        this.mainFrame.setVisible(true);
    }

    @Override
    public void addNode(int indentation, String text)
    {
        Border margin = new EmptyBorder(0,0,0,0);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Helvetica", Font.BOLD,DEFAULT_LABEL_HEIGHT));

        label.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), margin));//temp

        label.setBounds(this.createBounds(indentation,0, DEFAULT_LABEL_WIDTH-15, DEFAULT_LABEL_HEIGHT));

        JLabel label2 = new JLabel("Some data");
        label2.setFont(new Font("Helvetica", Font.BOLD,8));

        label2.setBorder(new CompoundBorder(new LineBorder(Color.RED), margin));//temp

        label2.setBounds(this.createBounds(indentation, DEFAULT_LABEL_HEIGHT, DEFAULT_LABEL_WIDTH-15, 20));

        JLabel label3 = new JLabel("other data");
        label3.setFont(new Font("Helvetica", Font.BOLD,DEFAULT_LABEL_HEIGHT));

        label3.setBorder(new CompoundBorder(new LineBorder(Color.BLUE), margin));//temp

        label3.setBounds(this.createBounds(indentation,DEFAULT_LABEL_HEIGHT+20, DEFAULT_LABEL_WIDTH-15, DEFAULT_LABEL_HEIGHT));
        this.labelList.put(label, label.getFont().getSize());
        this.labelList.put(label2, label2.getFont().getSize());
        this.labelList.put(label3, label3.getFont().getSize());
        this.slide.add(label);
        this.slide.add(label2);
        this.slide.add(label3);
        this.slide.repaint();
    }

    private Rectangle createBounds(int x, int y, int width, int height)
    {
        return new Rectangle(X_MARGIN + x, Y_MARGIN + y, width-10, height);
    }
}
