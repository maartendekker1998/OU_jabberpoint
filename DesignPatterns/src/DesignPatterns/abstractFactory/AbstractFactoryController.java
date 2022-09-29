package DesignPatterns.abstractFactory;

import DesignPatterns.MainController;
import DesignPatterns.abstractFactory.bombed.BombedMazeFactory;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class AbstractFactoryController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button buttonMakeMaze = new Button("MakeMaze");
        buttonMakeMaze.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> this.makeMaze());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(buttonMakeMaze,0,1);
        super.main.getChildren().add(mainGrid);
    }

    private void makeMaze()
    {
        MazeGame mazeGame = new MazeGame();
        MazeFactory factory = new BombedMazeFactory();
        Maze maze = mazeGame.createMaze(factory);
    }
}