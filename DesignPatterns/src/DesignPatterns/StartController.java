package DesignPatterns;

import DesignPatterns.slideshow.*;
import DesignPatterns.slideshow.Text;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartController extends MainController
{
    @FXML
    private GridPane mainGrid = new GridPane();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(() -> super.setStage(super.main));

        Button statePattern = new Button("State");
        statePattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("state"));
        Button bridgePattern = new Button("Bridge");
        bridgePattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("bridge"));
        Button decoratorPattern = new Button("Decorator");
        decoratorPattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("decorator"));
        Button abstractFactoryPattern = new Button("AbstractFactory");
        abstractFactoryPattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("abstractfactory"));
        Button builderPattern = new Button("Builder");
        builderPattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("builder"));
        Button compositePattern = new Button("Composite");
        compositePattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("composite"));
        Button commandPattern = new Button("Command");
        commandPattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("command"));
        Button observerPattern = new Button("Observer");
        observerPattern.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("observer"));

        Button slideshow = new Button("Slideshow");
        slideshow.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> this.slideshow());
        this.mainGrid.add(statePattern,0,0);
        this.mainGrid.add(bridgePattern,0,1);
        this.mainGrid.add(decoratorPattern, 0, 2);
        this.mainGrid.add(abstractFactoryPattern,0,3);
        this.mainGrid.add(builderPattern, 0, 4);
        this.mainGrid.add(compositePattern, 0, 5);
        this.mainGrid.add(commandPattern, 0, 6);
        this.mainGrid.add(observerPattern,0,7);

        this.mainGrid.add(slideshow, 0, 10);
        this.mainGrid.setMinWidth(250);
        super.main.getChildren().add(this.mainGrid);
    }

    private Projector projector;

    {
        DomainFacade facade = new DomainFacade();
        facade.build();
        this.projector = facade.getProjector();
    }

    private void slideshow()
    {
        Button slideshow = new Button("Next slide");
        slideshow.setMinWidth(80);
        slideshow.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> this.slideshow());
        GridPane slidePane = new GridPane();
        slidePane.setMinWidth(250);
        slidePane.add(slideshow, 0, 0);
        slidePane.getColumnConstraints().addAll(new ColumnConstraints(20), new ColumnConstraints(20));

        SlideShowComponent slide = this.projector.getNextSlide(); //Slide.class
        List<? extends Content> content = slide.getContent();
        this.renderSlide(content, slidePane);
        Platform.runLater(() -> this.main.getChildren().set(0, slidePane));
    }

    private int index = 0;

    private void renderSlide(List<? extends Content> contents, GridPane slidePane)
    {
        for (Content content : contents) //List<Content>.class
        {
            index++;
            String data;
            Label label = new Label();
            label.setMinWidth(230);
            if (content instanceof Text)
            {
                Text textComponent = ((Text)content);
                data = textComponent.getData();
                label.setText(data);
                slidePane.add(label, textComponent.getIndentation(), index);
                continue;
            }
            if (content instanceof Image)
            {
                Image imageContent = ((Image)content);
                data = imageContent.getData();
                label.setText(data);
                slidePane.add(label, imageContent.getIndentation(), index);
                continue;
            }
            if (content instanceof BulletList)
            {
                index--;
                List<? extends Content> childes = content.getContent();
                this.renderSlide(childes, slidePane);
            }
        }
    }
}
