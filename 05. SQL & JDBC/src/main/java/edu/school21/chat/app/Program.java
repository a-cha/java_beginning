package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.implementations.ChatroomsRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/antoncaparin";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "";
    private static final String DB_SCHEMA = "./src/main/java/resources/schema.sql";
    private static final String DB_DATA = "./src/main/java/resources/data.sql";
    private static final DataSource ds;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);

        ds = new HikariDataSource(config);
    }

    public static void main(String[] argv) {
//        createDatabase();

        System.out.println("Enter a message ID");
        new ChatroomsRepositoryJdbcImpl(ds)
                .findById(new Scanner(System.in).nextLong())
                .ifPresent(System.out::println);

        System.exit(0);
    }

    private static void createDatabase() {
        System.out.println("Creating database...");
        try {
            ds.getConnection().createStatement().execute(
                    new String(Files.readAllBytes(Paths.get(DB_SCHEMA)))
            );
            ds.getConnection().createStatement().execute(
                    new String(Files.readAllBytes(Paths.get(DB_DATA)))
            );
            System.out.println("Database created");
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
