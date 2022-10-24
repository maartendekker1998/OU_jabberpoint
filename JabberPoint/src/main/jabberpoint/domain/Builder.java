package main.jabberpoint.domain;

import java.util.List;

public interface Builder
{
    void newSlideShow();
    void newSlide();
    void addSlideTitle(String title);

    void setSlideTransitions(Boolean transitions);

    void addTextContent(Integer indentation, String data);
    void addImageContent(Integer indentation, String data);
    void addSlide();
    SlideShowComponent getSlideShow();

    void addBulletList(Integer indentation, List<Content> contentList);
}
