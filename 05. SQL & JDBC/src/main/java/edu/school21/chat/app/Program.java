package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

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

/*
//  01 exercise
        System.out.println("Enter a message ID");
        new MessagesRepositoryJdbcImpl(ds)
                .findById(new Scanner(System.in).nextLong())
                .ifPresent(System.out::println);
*/
/*
//  02 exercise
        User creator = new User(4L, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(2L, "room", creator, new ArrayList<>());
        Message message = new Message(null, creator, room, "lol!", new Timestamp(System.currentTimeMillis()));
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        messagesRepository.save(message);
        System.out.println(message.getId());
        System.out.println(new MessagesRepositoryJdbcImpl(ds).findById(message.getId()).get());
*/
/*
//  03 exercise
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        Optional<Message> messageOptional = messagesRepository.findById(18L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("YEAAHHHHH");
            message.setDateTime(null);
            messagesRepository.update(message);
        }
*/

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
