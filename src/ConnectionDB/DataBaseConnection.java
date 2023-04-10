package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hw_21_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

// Create table in DB
    public void createDatabase() throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "CREATE DATABASE IF NOT EXISTS hw_21_db";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        }

        try (Connection connection = this.getConnection()) {
            String query = "USE hw_21_db";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        }

        try (Connection connection = this.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS src.table.Homework (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL," +
                    "description VARCHAR(255) NOT NULL" +
                    ")";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        }

        try (Connection connection = this.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS src.table.Lesson (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL," +
                    "updatedAt TIMESTAMP," +
                    "homework_id INT," +
                    "FOREIGN KEY (homework_id) REFERENCES src.table.table.Homework(id)" +
                    ")";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        }

    }
}
