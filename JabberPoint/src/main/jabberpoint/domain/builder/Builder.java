package main.jabberpoint.domain.builder;

import main.jabberpoint.domain.components.BulletList;
import main.jabberpoint.domain.components.SlideShowComponent;

public interface Builder
{
    void newSlideShow();
    SlideShowComponent getSlideShow();

    void newSlide();
    void setSlideTransitions(Boolean transitions);
    void addSlideTitle(String title);
    void addTextContent(Integer indentation, String data);
    void addImageContent(Integer indentation, String data);
    BulletList newBulletList(int indentation);
    void addBulletList(Integer indentation, BulletList contentList);
    void addSlide();

    void newStyles();
    void addStyle(String key, String value);

    void newMetadata();
    void setMetadata(String key, String value);
    void addMetadata();
}
