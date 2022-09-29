package DesignPatterns.decorator;

import DesignPatterns.MainController;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class DecoratorController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button buttonDecorator = new Button("Decorator");
        buttonDecorator.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> decorator());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(buttonDecorator,0, 1);
        super.main.getChildren().add(mainGrid);
    }

    private void decorator()
    {
        Window window = new Window();
        TextView textView = new TextView();
        window.setContents(textView);
        window.setContents(new BorderDecorator(textView, 1));
        window.setContents(new BorderDecorator(new ScrollDecorator(textView), 1));
    }
}