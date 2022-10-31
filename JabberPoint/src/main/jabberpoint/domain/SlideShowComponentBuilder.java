package main.jabberpoint.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlideShowComponentBuilder implements Builder
{
    private ConcreteSlideShow slideShow;
    private ConcreteSlide slide;
    private Map<String, String> metadata;
    private Map<String, String> styles;

    @Override
    public void newSlideShow() {
        this.slideShow = new ConcreteSlideShow();
    }
    @Override
    public SlideShowComponent getSlideShow(){
        return this.slideShow;
    }
    
    @Override
    public void newSlide() {
        this.slide = new ConcreteSlide();
    }
    @Override
    public void setSlideTransitions(Boolean transitions){
        this.slide.setHasTransitions(transitions);
    }
    @Override
    public void addSlideTitle(String title){
        if (!this.styles.isEmpty()) {
            slide.addStyles(this.styles);
        }
        this.slide.setTitle(title);
    }
    private void addContent(Content content) {
        if (this.styles.isEmpty()){
            this.slide.addContent(content);
        }else{
            content.addStyles(this.styles);
            this.slide.addContent(content);
        }
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
    public BulletList newBulletList(int indentation){
        if (this.styles.isEmpty()){
            return new BulletList(indentation, new ArrayList<>());
        }else{
            BulletList bulletList = new BulletList(indentation, new ArrayList<>());
            bulletList.addStyles(this.styles);
            return bulletList;
        }
    }
    @Override
    public void addBulletList(Integer indentation, BulletList contentList) {
        this.addContent(contentList);
    }
    @Override
    public void addSlide() {
        this.slideShow.addSlide(this.slide);
    }

    @Override
    public void newStyles(){
        this.styles = new HashMap<>();
    }
    @Override
    public void addStyle(String key, String value){
        this.styles.put(key, value);
    }

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
