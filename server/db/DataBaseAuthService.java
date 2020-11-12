package ru.kaushly.java.cloudservis.db;


import ru.kaushly.java.cloudservis.server.AuthService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseAuthService implements AuthService {
    private Connection connection;

    public DataBaseAuthService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public AuthService.Record findRecord(String login, String password) throws RuntimeException {
//        String sql = "SELECT id, name FROM USERS where login = ? and password = ?";
        String sql = "INSERT INTO [USERS] ([LOGIN], [PASSWORD]) VALUES where login = ? and password = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
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
