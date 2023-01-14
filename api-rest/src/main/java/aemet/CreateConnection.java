package aemet;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {
    public CreateConnection(String name) {
        this.name = name;
    }

    private String name;

    public Connection connect() {
        Connection conn = null;
        String path = System.getProperty("user.dir") + "\\" + name;
        try {
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String uri = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\" + name  + "\\"+ name + ".db" ;
            conn = DriverManager.getConnection(uri);


            return conn;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
