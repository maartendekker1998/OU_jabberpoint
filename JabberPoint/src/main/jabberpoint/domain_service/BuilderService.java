package main.jabberpoint.domain_service;

import main.jabberpoint.domain.Builder;
import main.jabberpoint.domain.SlideShowComponent;

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

    public void addTextContent(Integer indentation, String data){
        builder.addTextContent(indentation, data);
    }

    public void addImageContent(Integer indentation, String data){
        builder.addImageContent(indentation, data);
    }

    public void addSlide(){
        builder.addSlide();
    }

    public SlideShowComponent getResults() {
        return builder.getSlideShow();
    }
}
