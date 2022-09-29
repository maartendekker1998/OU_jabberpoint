package DesignPatterns;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class MainController implements Initializable
{
    @FXML
    private static Stage stage;

    @FXML
    protected GridPane main;

    void setStage(Node currentView)
    {
        stage = (Stage)currentView.getScene().getWindow();
    }

    protected void openNewView(String file)
    {
        Platform.runLater(() ->
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(file + ".fxml"));
                Scene scene = new Scene(loader.load(), stage.getScene().getWidth(), stage.getScene().getHeight());
                stage.setScene(scene);
                stage.setTitle(file);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}