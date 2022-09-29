package DesignPatterns.bridge;

import DesignPatterns.MainController;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class BridgeController extends MainController
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button buttonCreateQueueWithVectorImp = new Button("Vector with Queue");
        buttonCreateQueueWithVectorImp.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> makeQueueWithVectorImp());
        Button buttonCreateQueueWithLinkedListImp = new Button("LinkedList with Queue");
        buttonCreateQueueWithLinkedListImp.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> makeQueueWithLinkedListImp());
        Button buttonCreateStackWithVectorImp = new Button("Vector with Stack");
        buttonCreateStackWithVectorImp.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> makeStackWithVectorImp());
        Button buttonCreateStackWithLinkedListImp = new Button("LinkedList with Stack");
        buttonCreateStackWithLinkedListImp.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> makeStackWithLinkedListImp());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(buttonCreateQueueWithVectorImp,0, 1);
        mainGrid.add(buttonCreateQueueWithLinkedListImp,0,2);
        mainGrid.add(buttonCreateStackWithVectorImp,0,3);
        mainGrid.add(buttonCreateStackWithLinkedListImp,0,4);
        super.main.getChildren().add(mainGrid);
    }

    private void makeQueueWithVectorImp()
    {
        new Queue(new Vector());
    }

    private void makeQueueWithLinkedListImp()
    {
        new Queue(new LinkedList());
    }

    private void makeStackWithVectorImp()
    {
        new Stack(new Vector());
    }

    private void makeStackWithLinkedListImp()
    {
        new Stack(new LinkedList());
    }
}