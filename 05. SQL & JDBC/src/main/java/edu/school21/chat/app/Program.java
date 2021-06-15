package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

public class Program
{
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/sadolph";
    static final String USER = "postgres";
    static final String PASS = "";
    public static Statement statement = null;

    static {
        Connection connection = getConnection();
        if (connection == null) System.exit(-1);
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) throws SQLException, IOException {
        String data = new String(Files.readAllBytes(Paths.get("./src/main/java/resources/schema.sql")));
        statement.execute(data);

        data = new String(Files.readAllBytes(Paths.get("./src/main/java/resources/data.sql")));
        statement.execute(data);

        MessagesRepository repo = new MessagesRepositoryJdbcImpl();
        Optional<Message> optionalMessage = repo.findById(5L);
        Message message = optionalMessage.orElse(null);
        System.out.println(message.toString());
        System.exit(0);
    }

    private static Connection getConnection() {
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return null;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }

        return connection;
    }

}
