package DesignPatterns.builder;

import DesignPatterns.MainController;
import DesignPatterns.abstractFactory.Maze;
import DesignPatterns.abstractFactory.MazeGame;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class BuilderController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button build = new Button("Build");
        build.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> builder());
        Button buildCounting = new Button("Build Counting");
        buildCounting.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> buildCounting());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(build,0, 1);
        mainGrid.add(buildCounting, 0, 2);
        super.main.getChildren().add(mainGrid);
    }

    private void builder()
    {
        Maze maze;
        MazeGame game = new MazeGame();
        StandardMazeBuilder builder = new StandardMazeBuilder();
        game.createMaze(builder);
        maze = builder.getMaze();
    }

    private void buildCounting()
    {
        int doors = 0, rooms = 0;
        MazeGame game = new MazeGame();
        CountingMazeBuilder builder = new CountingMazeBuilder();
        game.createMaze(builder);
        builder.getCounts(doors, rooms);

        //Usage of Java getters
        System.out.println("The maze has " + builder.getRooms() + " rooms and " + builder.getDoors() + " doors");
    }
}