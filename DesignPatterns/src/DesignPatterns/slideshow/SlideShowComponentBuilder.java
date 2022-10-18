package DesignPatterns.slideshow;

import java.util.ArrayList;
import java.util.List;

public class SlideShowComponentBuilder implements Builder
{
    private SlideShowComponent slideShowComponent;

    @Override
    public void build()
    {
        List<ConcreteSlide> slides = new ArrayList<>();
        List<Content> items = new ArrayList<>();
        Text text = new Text(0, "<h1>epic data</h1>");
        items.add(text);
        ConcreteSlide slideWithTextOnly = new ConcreteSlide(items);
        Image image = new Image(0, "<h1>epic data</h1>");
        items.add(image);
        ConcreteSlide slideWithImageAndText = new ConcreteSlide(items);

        slides.add(slideWithTextOnly);
        slides.add(slideWithImageAndText);

        items.clear();
        items.add(new Text(0, "<h1>epic data</h1>"));
        items.add(new BulletList(1, new Text(1, "<h2>epic data</h2>"), new BulletList(2, new Text(2, "<h3>epic data</h3>"), new Image(2, "<img>epic image</img>"))));
        items.add(new Image(0, "<h1>epic data</h1>"));
        ConcreteSlide slideWithBulletList = new ConcreteSlide(items);
        slides.add(slideWithBulletList);
        this.slideShowComponent = new SlideShowConcrete(slides);
    }

    @Override
    public SlideShowComponent getSlideShow()
    {
        return this.slideShowComponent;
    }
}
