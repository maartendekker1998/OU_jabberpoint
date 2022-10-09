package DesignPatterns.composite;

import DesignPatterns.MainController;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CompositeController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button buttonDecorator = new Button("Composite");
        buttonDecorator.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> composite());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(buttonDecorator,0, 1);
        super.main.getChildren().add(mainGrid);
    }

    private void composite()
    {
        Equipment drive = new Floppy();
        System.out.println(drive.netPrice()); //Should be floppy price
        List<Equipment> equipment = new ArrayList<>();
        equipment.add(new Floppy());
        equipment.add(new Floppy());
        Equipment chasisWithFloppy = new Chasis(equipment);
        System.out.println(chasisWithFloppy.netPrice()); //Should be 2* floppy price
    }
}