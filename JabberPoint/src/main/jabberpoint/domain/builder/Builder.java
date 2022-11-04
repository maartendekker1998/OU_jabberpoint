package main.jabberpoint.domain.builder;

import main.jabberpoint.domain.components.BulletList;
import main.jabberpoint.domain.components.Image;
import main.jabberpoint.domain.components.SlideShowComponent;
import main.jabberpoint.domain.components.Text;

/**
 * Part of Builder Pattern
 * Role: Builder interface
 */
public interface Builder
{
    /**
     * creates a new empty slideshow
     */
    void newSlideShow();

    /**
     * gets the result of the builder
     * @return a fully built slideshow
     */
    SlideShowComponent getSlideShow();

    /**
     * creates a new empty slide
     */
    void newSlide();

    /**
     * sets is the slide has transitions
     * @param transitions true or false
     */
    void setSlideTransitions(boolean transitions);

    /**
     * adds a title to a slide
     * @param title slidetitle
     */
    void addSlideTitle(String title);

    /**
     * adds a Text item
     * @see Text
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual data
     */
    void addTextContent(Integer indentation, String data);

    /**
     * adds a Image item
     * @see Image
     * @param indentation to specify the indentation of the item
     * @param data to specify the actual data
     */
    void addImageContent(Integer indentation, String data);

    /**
     * creates a new empty bullet list
     * @param indentation to specify the indentation of the item
     * @return a new bulletlist
     */
    BulletList newBulletList(int indentation);

    /**
     * adds a BulletList item
     * @see BulletList
     * @param indentation to specify the indentation of the item
     * @param contentList to specify the actual content
     */
    void addBulletList(int indentation, BulletList contentList);

    /**
     * adds a slide to the slideshow
     */
    void addSlide();

    /**
     * creates a new styles map
     */
    void newStyles();

    /**
     * adds a style key value pair to the styles map
     * @param key the name of the style category (e.g. font)
     * @param value the value of the style (e.g. Arial)
     */
    void addStyle(String key, String value);

    /**
     * creates a new empty metadata map
     */
    void newMetadata();

    /**
     * adds a metadata key value pair to the metdata map
     * @param key the name of the metadata category (e.g. showtitle)
     * @param value the value of the metadata (e.g. demo-presentation)
     */
    void setMetadata(String key, String value);

    /**
     * adds the metadata map to the slide
     */
    void addMetadata();
}
