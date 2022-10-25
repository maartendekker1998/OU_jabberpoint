package main.jabberpoint.domain;

public class SlideShowComponentBuilder implements Builder
{
    private ConcreteSlideShow slideShow;
    private ConcreteSlide slide;

    @Override
    //reset function for the builder
    public void newSlideShow() {
        this.slideShow = new ConcreteSlideShow();
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

    @Override
    public void setSlideTransitions(Boolean transitions){
        this.slide.setHasTransitions(transitions);
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
        this.slideShow.addSlide(this.slide);
    }

    @Override
    public SlideShowComponent getSlideShow(){
        return this.slideShow;
    }

    @Override
    public void addBulletList(Integer indentation, BulletList contentList) {
        this.addContent(contentList);
    }
}
