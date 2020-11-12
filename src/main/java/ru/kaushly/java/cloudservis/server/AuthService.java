package ru.kaushly.java.cloudservis.server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement statement;
    private static final String DATABASE_NAME = "/users";
    private static final String URL = "jdbc:postgresql://localhost:5432/UsersOfCloud" + DATABASE_NAME;
    private static final String DB_USER ="postgres";
    private static final String DB_PASS ="postgres";


    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String checkAuthorization(String login, String password) {
        String sql = String.format("SELECT id FROM users WHERE name = '%s' AND password = '%s'", login, password);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("username_fld");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkLogin(String login) {
        String sql = String.format("SELECT name FROM users WHERE name = '%s'", login);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String tryRegister(String login, String password) {
        String sql = String.format("INSERT INTO users(name, password) VALUES('%s','%s')", login, password);
        try {
            statement.executeQuery(sql);
            return login;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
