package ru.kaushly.java.cloudservis.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.kaushly.java.cloudservis.server.AuthService;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Authorization implements Initializable {

    @FXML
    Button open;

    @FXML
    Button reg;

    @FXML
    TextField login;

    @FXML
    TextField password;

    public void btnEnterCloudAction(ActionEvent actionEvent) throws IOException {
        AuthService.checkAuthorization(login.getText(), password.getText());
        //todo проверка в БД
        Stage stage = (Stage) open.getScene().getWindow();
        stage.close();
        Parent second = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Stage secondWindow = new Stage();
        secondWindow.setTitle("Cloud");
        secondWindow.initModality(Modality.WINDOW_MODAL);
        secondWindow.setScene(new Scene(second, 800, 600));
        secondWindow.show();
    }

    public void btnRegAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) reg.getScene().getWindow();
        stage.close();
        Parent reg = FXMLLoader.load(getClass().getResource("/regist.fxml"));
        Stage secondWindow = new Stage();
        secondWindow.setTitle("Регистрация");
        secondWindow.initModality(Modality.APPLICATION_MODAL);
        secondWindow.setScene(new Scene(reg, 300, 200));
        secondWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
