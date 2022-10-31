package main.jabberpoint.domain_service;

import main.jabberpoint.domain.builder.Builder;
import main.jabberpoint.domain.components.*;

public class BuilderService {

    private final Builder builder;

    public BuilderService(Builder builder){
        this.builder = builder;
    }


    public void newSlideShow(){
        builder.newSlideShow();
    }

    public SlideShowComponent getSlideShow() {
        return builder.getSlideShow();
    }

    public void newSlide(){
        builder.newSlide();
    }

    public void setTransitions(Boolean transitions){
        builder.setSlideTransitions(transitions);
    }

    public void addSlideTitle(String title){
        builder.addSlideTitle(title);
    }

    public void addTextContent(Integer indentation, String data){
        builder.addTextContent(indentation, data);
    }

    public void addImageContent(Integer indentation, String data){
        builder.addImageContent(indentation, data);
    }

    public BulletList newBulletList(int indentation)
    {
        return builder.newBulletList(indentation);
    }

    public void addBulletList(Integer indentation, BulletList contentList){
        builder.addBulletList(indentation, contentList);
    }

    public void addTextToBullet(int indentation, String data, ContentComposite bulletList)
    {
        bulletList.addContent(new Text(indentation, data));
    }

    public void addImageToBullet(int indentation, String data, ContentComposite bulletList)
    {
        bulletList.addContent(new Image(indentation, data));
    }

    public void addSlide(){
        builder.addSlide();
    }

    public void newStyles(){
        builder.newStyles();
    }

    public void addStyle(String key, String value){
        builder.addStyle(key, value);
    }

    public void newMetaData(){
        builder.newMetadata();
    }

    public void setMetadata(String key, String value){
        builder.setMetadata(key, value);
    }

    public void addMetadata(){
        builder.addMetadata();
    }
}
