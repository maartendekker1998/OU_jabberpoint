package main.jabberpoint.domain;

public interface Builder
{
    void newSlideShow();
    void newSlide();

    void newMetadata();

    void setMetadata(String key, String value);

    void addMetadata();

    void addSlideTitle(String title);

    void setSlideTransitions(Boolean transitions);

    void addTextContent(Integer indentation, String data);
    void addImageContent(Integer indentation, String data);
    void addSlide();
    SlideShowComponent getSlideShow();
    void addBulletList(Integer indentation, BulletList contentList);
}
