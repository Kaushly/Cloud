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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Registration implements Initializable, AuthService {

    private Connection connection;

    public Registration(Connection connection) {
        this.connection = connection;
    }

    @FXML
    Button enter;

    @FXML
    TextField addLogin;

    @FXML
    TextField addPassword;

    public void btnEnterCloudAction(ActionEvent actionEvent) throws IOException {
        // todo проверка на уникальность и добавление в БД
        findRecord(addLogin.getText(), addPassword.getText());
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

    @Override
    public Record findRecord(String login, String password) {
        String sql = "INSERT INTO [USERS] ([LOGIN], [PASSWORD]) VALUES where login = ? and password = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.first()){
                String name = resultSet.getString(2);
                return new AuthService.Record(login, password);
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException("Не удалось прочитать запись о пользователе из БД!", sqlException);
        }
        return null;
    }
}
