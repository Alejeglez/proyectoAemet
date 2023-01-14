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

            String defPath = System.getProperty("user.dir") + "\\" + name + "\\" + name + ".db";
            File file = new File(defPath);
            if(file.exists()){
                file.delete();
            }

            String uri = "jdbc:sqlite:" + defPath ;
            conn = DriverManager.getConnection(uri);

            return conn;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
