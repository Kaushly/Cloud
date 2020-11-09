package ru.kaushly.java.cloudservis.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Registration implements Initializable {

    @FXML
    Button enter;

    public void btnEnterCloudAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) enter.getScene().getWindow();
        stage.close();
        Parent second = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Stage secondWindow = new Stage();
        secondWindow.setTitle("Cloud");
        secondWindow.initModality(Modality.WINDOW_MODAL);
        secondWindow.setScene(new Scene(second, 800, 600));
        secondWindow.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
