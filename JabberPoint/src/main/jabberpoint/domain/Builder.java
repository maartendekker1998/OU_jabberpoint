package main.jabberpoint.domain;

public interface Builder
{
    void newSlideShow();
    void newSlide();
    void addSlideTitle(String title);
    void addTextContent(Integer indentation, String data);
    void addImageContent(Integer indentation, String data);
    void addSlide();
    SlideShowComponent getSlideShow();
}
