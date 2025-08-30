package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pgfinder",
                "root",
                "manish@99077"
            );
        } catch (ClassNotFoundException e) {
            System.out.println("Driver nahi mila!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection fail hua!");
            e.printStackTrace();
        }
        return conn;
    }
}