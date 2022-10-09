package DesignPatterns.observer;

import DesignPatterns.MainController;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ObserverController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button buttonObserver = new Button("Observe");
        buttonObserver.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> observer());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(buttonObserver,0, 1);
        super.main.getChildren().add(mainGrid);
    }

    private void observer()
    {
        ClockTimer clockTimer = new ClockTimer();
        DigitalClock digitalClock = new DigitalClock(clockTimer);
        AnalogClock analogClock = new AnalogClock(clockTimer);
        clockTimer.tick();
        digitalClock.detach();
        clockTimer.tick(); //Should only print analog clock
        analogClock.detach();
    }
}