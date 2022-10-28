package main.jabberpoint.domain_service;

import main.jabberpoint.domain.*;

import java.util.ArrayList;

public class BuilderService {

    private Builder builder;

    public BuilderService(Builder builder){
        this.builder = builder;
    }

    public void newSlideShow(){
        builder.newSlideShow();
    }

    public void newSlide(){
        builder.newSlide();
    }

    public void addSlideTitle(String title){
        builder.addSlideTitle(title);
    }

    public void setTransitions(Boolean transitions){
        builder.setSlideTransitions(transitions);
    }

    public void addTextContent(Integer indentation, String data){
        builder.addTextContent(indentation, data);
    }

    public void addImageContent(Integer indentation, String data){
        builder.addImageContent(indentation, data);
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

    public SlideShowComponent getResults() {
        return builder.getSlideShow();
    }

    public BulletList createBulletList(int indentation)
    {
        return new BulletList(indentation, new ArrayList<>());
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
