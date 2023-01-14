package aemet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DdlTranslator {

    private Connection conn;

    public DdlTranslator(Connection conn) {
        this.conn = conn;
    }

    public void createTableMinTemperatures() throws SQLException {

        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS MinTemperatures (" +
                "DATE TEXT NOT NULL PRIMARY KEY," +
                "TIME TEXT ," +
                "PLACE TEXT ,"+
                "STATION TEXT ," +
                "VALUE REAL" +
                ")");
    }

    public void createTableMaxTemperatures() throws SQLException {

        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS MaxTemperatures (" +
                "DATE TEXT NOT NULL PRIMARY KEY," +
                "TIME TEXT ," +
                "PLACE TEXT ,"+
                "STATION TEXT ," +
                "VALUE REAL" +
                ")");
    }
}
