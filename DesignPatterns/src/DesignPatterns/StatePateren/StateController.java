package DesignPatterns.StatePateren;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import DesignPatterns.MainController;
import java.net.URL;
import java.util.ResourceBundle;

public class StateController extends MainController
{
    private TCPConnection tcpConnection;

    public StateController()
    {
        this.tcpConnection = new TCPConnection();
    }

    @FXML
    private Label state = new Label("state: closed");

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        GridPane mainGrid = new GridPane();
        Button buttonBack = new Button("Back");
        buttonBack.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> super.openNewView("start"));
        Button activeOpen = new Button("Active Open");
        activeOpen.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> activeOpen());
        Button passiveOpen = new Button("Active Open");
        passiveOpen.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> passiveOpen());
        Button close = new Button("Close");
        close.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> close());
        Button transmit = new Button("Transmit");
        transmit.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> transmit());
        mainGrid.setMinWidth(250);
        mainGrid.add(buttonBack,0, 0);
        mainGrid.add(activeOpen, 0, 1);
        mainGrid.add(passiveOpen, 0, 2);
        mainGrid.add(close, 0, 3);
        mainGrid.add(transmit, 0, 4);
        mainGrid.add(this.state, 0, 5);
        super.main.getChildren().add(mainGrid);
    }

    private void activeOpen()
    {
        this.tcpConnection.activeOpen();
        this.state.setText("state: active opened");
    }

    private void passiveOpen()
    {
        this.tcpConnection.passiveOpen();
        this.state.setText("state: passive opened");
    }

    private void close()
    {
        this.tcpConnection.close();
        this.state.setText("state: closed");
    }

    private void transmit()
    {
        this.tcpConnection.ack();
        this.state.setText("state: transmitting");
    }
}


