package main.jabberpoint.domain;

import java.util.HashMap;
import java.util.Map;

public class SlideShowComponentBuilder implements Builder
{
    private ConcreteSlideShow slideShow;
    private ConcreteSlide slide;
    private Map<String, String> metadata;

    //Slideshow related functions
    @Override
    public void newSlideShow() {
        this.slideShow = new ConcreteSlideShow();
    }

    @Override
    public SlideShowComponent getSlideShow(){
        return this.slideShow;
    }


    //Slide related functions
    @Override
    public void newSlide() {
        //reset function for slide
        this.slide = new ConcreteSlide();
    }
    @Override
    public void addSlide() {
        this.slideShow.addSlide(this.slide);
    }
    @Override
    public void addSlideTitle(String title){
        this.slide.setTitle(title);
    }
    @Override
    public void setSlideTransitions(Boolean transitions){
        this.slide.setHasTransitions(transitions);
    }


    // Content related functions
    private void addContent(Content content) {
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
    public void addBulletList(Integer indentation, BulletList contentList) {
        this.addContent(contentList);
    }


    // Metadata related functions
    @Override
    public void newMetadata(){
        this.metadata = new HashMap<>();
    }
    @Override
    public void setMetadata(String key, String value){
        metadata.put(key, value);
    }
    @Override
    public void addMetadata() {
        Metadata.getInstance().initialize(this.metadata);
    }
}
