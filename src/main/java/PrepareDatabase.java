import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class PrepareDatabase {
    private static final String url = "jdbc:postgresql://localhost:5432/web_server_db";  // todo move to config
    private static final String user = "web_server_user";
    private static final String password = "1";

    public static void main(String[] args) throws SQLException, IOException {
        PrepareDatabase.initDB();
        PrepareDatabase.fillDB();
    }

    public static void initDB() throws SQLException, IOException {
        String text = Files.readString(Paths.get(
                "/home/maria/Desktop/web-server-java/SQLscripts/tables_creation.sql"));
        Connection con = DriverManager.getConnection(
                PrepareDatabase.url, PrepareDatabase.user, PrepareDatabase.password);
        Statement st = con.createStatement();
        st.executeUpdate(text);
    }

    public static void fillDB() throws SQLException, IOException {
        String text = Files.readString(Paths.get(
                "/home/maria/Desktop/web-server-java/SQLscripts/tables_fill.sql"));
        Connection con = DriverManager.getConnection(
                PrepareDatabase.url, PrepareDatabase.user, PrepareDatabase.password);
        Statement st = con.createStatement();
        st.executeUpdate(text);
    }
}
