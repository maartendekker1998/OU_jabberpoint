package DesignPatterns.slideshow;

import java.util.ArrayList;
import java.util.List;

public class Projector
{
    private SlideShow slideShow;

    public Projector()
    {
        this.something();
    }

    public void something()
    {
        List<Slide> slides = new ArrayList<>();
        List<SlideItem> items = new ArrayList<>();
        Text text = new Text();
        items.add(text);
        Slide slideWithTextOnly = new Slide(items);
        Slide slideWithTextOnly2 = new Slide(items);
        slides.add(slideWithTextOnly);
        slides.add(slideWithTextOnly2);
        slideShow = new SlideShow(slides);
        slideShow.start();
    }
}
