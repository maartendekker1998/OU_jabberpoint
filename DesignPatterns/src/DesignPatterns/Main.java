package DesignPatterns;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(super.getClass().getResource("start.fxml"));
        Parent parent = loader.load();
        primaryStage.setTitle("Start");
        Scene scene = new Scene(parent, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
//        concreteSlideShow slideShow = new concreteSlideShow(new Slide(
//                new Text("just a text"),
//                new bullet(new Text("- bullet text")))
//        );
//
//        List<?> slides = slideShow.getContent();
//        System.out.println("\nnow actually printing them: ");
//        slides.forEach(x -> ((List<?>)x).forEach(System.out::println));
    }
}

class concreteSlideShow
{
    private List<Slide> slides = new ArrayList<>();

    concreteSlideShow(Slide... slides)
    {
        this.slides.addAll(Arrays.asList(slides));
    }

    List<?> display()
    {
        List<? super Object> list = new ArrayList<>();
        System.out.println("Slideshow contains " + slides.size() + " slides");
        slides.forEach(slide ->
        {
            list.add(slide.display());
        });
        return list;
    }
}

class Slide
{
    List<content<?>> content = new ArrayList<>();

    Slide(content<?>... content)
    {
        this.content.addAll(Arrays.asList(content));
    }

    List<?> display()
    {
        List<? super Object> list = new ArrayList<>();
        System.out.println("Slide contains " + this.content.size() + " contents");
        content.forEach(content ->
        {
            list.add(content.display());
        });
        return list;
    }
}

abstract class content<E>
{
    List<E> data = new ArrayList<>();
    int indentation = 1;
    public abstract List<E> display();
}

class bullet extends content<content<?>>
{
    bullet(content<?>... contents)
    {
        this.data.addAll(Arrays.asList(contents));
    }

    public List<content<?>> display()
    {
        System.out.println("Content is type bullet and has " + this.data.size() + " contents");
        return this.data;
    }
}

class Text extends content<String>
{
    Text(String data)
    {
        this.data.add(data);
    }

    public List<String> display()
    {
        System.out.println("Content is type Text and is \"" + this.toString() + "\"");
        return data;
    }

    @Override
    public String toString()
    {
        return this.data.get(0);
    }
}



/*
concreteSlideShow
{
    "slide":
    [
        {
            "content":
            [
                {
                    "data":
                    {

                    }
                },
                {
                    "data":
                    {

                    }
                }
            ]
        },
        {

        }
    ]
}



*/