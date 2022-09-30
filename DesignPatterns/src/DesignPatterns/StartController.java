package DesignPatterns;

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
        mainGrid.add(statePattern,0,0);
        mainGrid.add(bridgePattern,0,1);
        mainGrid.add(decoratorPattern, 0, 2);
        mainGrid.add(abstractFactoryPattern,0,3);
        mainGrid.add(builderPattern, 0, 4);
        mainGrid.setMinWidth(250);
        super.main.getChildren().add(mainGrid);
    }
}
