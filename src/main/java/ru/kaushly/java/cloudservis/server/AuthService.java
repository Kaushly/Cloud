package ru.kaushly.java.cloudservis.server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement statement;
    private static final String URL = "jdbc:postgresql://localhost:5432/UsersOfCloud";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";


    public static void connect() {
        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        connection = null;

        try {
            connection = DriverManager
                    .getConnection(URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static String checkAuthorization(String login, String password) {
        String sql = String.format("SELECT id FROM users WHERE name = '%s' AND password = '%s'", login, password);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("id");
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

    public static int tryRegister(String login, String password) {
        String sql = String.format("INSERT INTO users(name, password) VALUES('%s','%s')", login, password);
        try {
            statement.executeQuery(sql);
            return 2;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        String sql = String.format("INSERT INTO users(name, password) VALUES('%s','%s')", login, password);
//        try {
//            statement.executeQuery(sql);
//            return login;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return 0;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
