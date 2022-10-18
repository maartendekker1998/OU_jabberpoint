package DesignPatterns.slideshow;

class Director
{
    void constructSlideShow(Builder builder, Object... map)
    {
        //do this with map items and create sequence of building the complete slideshow
        builder.build();
    }
}
