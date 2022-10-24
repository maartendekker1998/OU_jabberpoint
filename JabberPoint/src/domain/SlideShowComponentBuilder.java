package domain;

import java.util.ArrayList;
import java.util.List;

public class SlideShowComponentBuilder implements Builder
{
    private SlideShowComponent slideShowComponent;
    private ConcreteSlide slide;
    private List<ConcreteSlide> slides;

    public void build()
    {
        List<ConcreteSlide> slides = new ArrayList<>();
        List<Content> items = new ArrayList<>();
        Text text = new Text(0, "<h1>epic data</h1>");
        items.add(text);
        ConcreteSlide slideWithTextOnly = new ConcreteSlide("Slide 1", items);
        Image image = new Image(0, "<h1>epic data</h1>");
        items.add(image);
        ConcreteSlide slideWithImageAndText = new ConcreteSlide("Slide 2", items);

        slides.add(slideWithTextOnly);
        slides.add(slideWithImageAndText);

        items.clear();
        items.add(new Text(0, "<h1>epic data</h1>"));
        items.add(new BulletList(1, new Text(1, "<h2>epic data</h2>"), new BulletList(2, new Text(2, "<h3>epic data</h3>"), new Image(2, "<img>epic image</img>"))));
        items.add(new Image(0, "<h1>epic data</h1>"));
        ConcreteSlide slideWithBulletList = new ConcreteSlide("Slide 3", items);
        slides.add(slideWithBulletList);
        this.slideShowComponent = new ConcreteSlideShow(slides);
    }

    @Override
    //reset function for the builder
    public void newSlideShow() {
        this.slideShowComponent = new ConcreteSlideShow();
        this.slides = new ArrayList<>();
    }

    @Override
    public void newSlide() {
        //reset function for slide
        this.slide = new ConcreteSlide();
    }

    @Override
    public void addSlideTitle(String title){
        this.slide.setTitle(title);
    }


    private void addContent(Content content) {
        //add content to local slide
        this.slide.addContent(content);
    }

    @Override
    public void addTextContent(Integer indentation, String data){
        this.addContent(new Text(indentation, data));
    }

    @Override
    public void addImageContent(Integer indentation, String data){
        this.addContent(new Image(indentation, data));
    }

    @Override
    public void addSlide() {
        //add the local slide to the slides
        this.slides.add(this.slide);
    }

    @Override
    public SlideShowComponent getSlideShow()
    {
        this.slideShowComponent = new ConcreteSlideShow(this.slides);
        return this.slideShowComponent;
    }
}
