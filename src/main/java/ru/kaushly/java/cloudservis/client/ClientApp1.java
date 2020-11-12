package ru.kaushly.java.cloudservis.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static ru.kaushly.java.cloudservis.server.NettyServer.PORT;

public class ClientApp1 extends Application {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public ClientApp1() throws IOException {
        socket = new Socket("localhost", PORT);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/auth.fxml"));
        primaryStage.setTitle("Вход");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
