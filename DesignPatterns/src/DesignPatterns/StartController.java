package DesignPatterns;

import DesignPatterns.slideshow.Projector;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(() -> super.setStage(super.main));
        GridPane mainGrid = new GridPane();
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
        mainGrid.add(statePattern,0,0);
        mainGrid.add(bridgePattern,0,1);
        mainGrid.add(decoratorPattern, 0, 2);
        mainGrid.add(abstractFactoryPattern,0,3);
        mainGrid.add(builderPattern, 0, 4);
        mainGrid.add(compositePattern, 0, 5);
        mainGrid.add(commandPattern, 0, 6);
        mainGrid.add(observerPattern,0,7);

        mainGrid.add(slideshow, 0, 10);
        mainGrid.setMinWidth(250);
        super.main.getChildren().add(mainGrid);
    }

    private void slideshow()
    {
        new Projector();
    }
}
