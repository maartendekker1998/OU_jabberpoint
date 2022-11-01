package main.jabberpoint.domain.builder;

import main.jabberpoint.domain.*;
import main.jabberpoint.domain.components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Part of Builder Pattern
 * Role: Concrete builder
 */
public class SlideShowComponentBuilder implements Builder
{
    private ConcreteSlideShow slideShow;
    private ConcreteSlide slide;
    private Map<String, String> metadata;
    private Map<String, String> styles;

    /**
     * creates a new empty slideshow
     */
    @Override
    public void newSlideShow() {
        this.slideShow = new ConcreteSlideShow();
    }

    /**
     * gets the result of the builder
     * @return a fully built slideshow
     */
    @Override
    public SlideShowComponent getSlideShow(){
        return this.slideShow;
    }

    /**
     * creates a new empty slide
     */
    @Override
    public void newSlide() {
        this.slide = new ConcreteSlide();
    }

    /**
     * sets is the slide has transitions
     * @param transitions true or false
     */
    @Override
    public void setSlideTransitions(Boolean transitions){
        this.slide.setHasTransitions(transitions);
    }

    /**
     * adds a title to a slide
     * @param title slidetitle
     */
    @Override
    public void addSlideTitle(String title){
        if (!this.styles.isEmpty()) {
            slide.addStyles(this.styles);
        }
        this.slide.setTitle(title);
    }

    /**
     * Adds content to the slide
     * @param content a text/image/bulletlist
     */
    private void addContent(Content content) {
        if (!this.styles.isEmpty()) {
            content.addStyles(this.styles);
        }
        this.slide.addContent(content);
    }

    /**
     * adds a Text item
     * @see Text
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual data
     */
    @Override
    public void addTextContent(Integer indentation, String data){
        this.addContent(new Text(indentation, data));
    }

    /**
     * adds a Image item
     * @see Image
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual data
     */
    @Override
    public void addImageContent(Integer indentation, String data){
        this.addContent(new Image(indentation, data));
    }

    /**
     * creates a new empty bullet list
     * @param indentation to specify the indentation of the item
     * @return a new bulletlist
     */
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

    /**
     * adds a BulletList item
     * @see BulletList
     * @param indentation to specify the indentation of the item
     * @param contentList to specify the actual content
     */
    @Override
    public void addBulletList(Integer indentation, BulletList contentList) {
        this.addContent(contentList);
    }

    /**
     * adds a slide to the slideshow
     */
    @Override
    public void addSlide() {
        this.slideShow.addSlide(this.slide);
    }

    /**
     * creates a new empty styles map
     */
    @Override
    public void newStyles(){
        this.styles = new HashMap<>();
    }

    /**
     * adds a style key value pair to the styles map
     * @param key the name of the style category (e.g. font)
     * @param value the value of the style (e.g. Arial)
     */
    @Override
    public void addStyle(String key, String value){
        this.styles.put(key, value);
    }

    /**
     * creates a new empty metadata map
     */
    @Override
    public void newMetadata(){
        this.metadata = new HashMap<>();
    }

    /**
     * adds a metadata key value pair to the metdata map
     * @param key the name of the metadata category (e.g. showtitle)
     * @param value the value of the metadata (e.g. demo-presentation)
     */
    @Override
    public void setMetadata(String key, String value){
        metadata.put(key, value);
    }

    /**
     * adds the metadata map to the slide
     */
    @Override
    public void addMetadata() {
        Metadata.getInstance().initialize(this.metadata);
    }
}
