package main.jabberpoint.domain_service;

import main.jabberpoint.domain.builder.Builder;
import main.jabberpoint.domain.components.*;

/**
 * This class serves as a domain service between the director and the builder
 */
public class BuilderService {

    private final Builder builder;

    /**
     * Sets the builder that this domain service will use
     * @param builder to build Slideshows
     * @see Builder
     */
    public BuilderService(Builder builder){
        this.builder = builder;
    }

    /**
     * creates a new slideshow
     */
    public void newSlideShow(){
        builder.newSlideShow();
    }

    /**
     * gets the result of the builder
     * @return a fully built slideshow
     */
    public SlideShowComponent getSlideShow() {
        return builder.getSlideShow();
    }

    /**
     * creates a new slide
     */
    public void newSlide(){
        builder.newSlide();
    }

    /**
     * sets is the slide has transitions
     * @param transitions true or false
     */
    public void setTransitions(Boolean transitions){
        builder.setSlideTransitions(transitions);
    }

    /**
     * adds a title to a slide
     * @param title slidetitle
     */
    public void addSlideTitle(String title){
        builder.addSlideTitle(title);
    }

    /**
     * adds a Text item
     * @see Text
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual data
     */
    public void addTextContent(Integer indentation, String data){
        builder.addTextContent(indentation, data);
    }

    /**
     * adds a Image item
     * @see Image
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual data
     */
    public void addImageContent(Integer indentation, String data){
        builder.addImageContent(indentation, data);
    }

    /**
     * creates a new bullet list
     * @param indentation to specify the indentation of the item
     * @return a new bulletlist
     */
    public BulletList newBulletList(int indentation)
    {
        return builder.newBulletList(indentation);
    }

    /**
     * adds a Bulletlist item
     * @see BulletList
     * @param indentation to specify the indentation of the item
     * @param contentList to specify the actual content
     */
    public void addBulletList(Integer indentation, BulletList contentList){
        builder.addBulletList(indentation, contentList);
    }

    /**
     * adds a text item to a bullet list
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual text
     * @param bulletList to specify the bulletlist the text will be added to
     */
    public void addTextToBullet(int indentation, String data, ContentComposite bulletList)
    {
        bulletList.addContent(new Text(indentation, data));
    }

    /**
     * adds a Image item to a bullet list
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual image
     * @param bulletList to specify the bulletlist the image will be added to
     */
    public void addImageToBullet(int indentation, String data, ContentComposite bulletList)
    {
        bulletList.addContent(new Image(indentation, data));
    }

    /**
     * adds a slide to the slideshow
     */
    public void addSlide(){
        builder.addSlide();
    }

    /**
     * creates a new styles map
     */
    public void newStyles(){
        builder.newStyles();
    }

    /**
     * adds a style key value pair to the styles map
     * @param key the name of the style category (e.g. font)
     * @param value the value of the style (e.g. Arial)
     */
    public void addStyle(String key, String value){
        builder.addStyle(key, value);
    }

    /**
     * creates a new metadata map
     */
    public void newMetaData(){
        builder.newMetadata();
    }

    /**
     * adds a metadata key value pair to the metdata map
     * @param key the name of the metadata category (e.g. showtitle)
     * @param value the value of the metadata (e.g. demo-presentation)
     */
    public void setMetadata(String key, String value){
        builder.setMetadata(key, value);
    }

    /**
     * adds the metadata map to the slide
     */
    public void addMetadata(){
        builder.addMetadata();
    }
}
