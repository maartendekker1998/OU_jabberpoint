package DesignPatterns.command;

import DesignPatterns.MainController;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class CommandController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button buttonCommand = new Button("Command");
        buttonCommand.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> command());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(buttonCommand,0, 1);
        super.main.getChildren().add(mainGrid);
    }

    private void command()
    {
        Command open = new OpenCommand(new Application());
        Command paste = new PasteCommand(new Document("My Document"));
        open.execute();
        paste.execute();

        MacroCommand macro = new MacroCommand();
        macro.add(open);
        macro.add(paste);
        macro.execute();
    }
}