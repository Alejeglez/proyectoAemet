package aemet;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {

    private CreateConnection createConnection;
    private Connection conn;
    private DatamartReader reader;

    public Controller() {
        this.createConnection = new CreateConnection("datamart");
    }

    public void run() throws SQLException {
        WebService apiAemet = new ApiAemet();
        apiAemet.launch();
    }

    public String getMinTemperatures() throws SQLException {
        conn = createConnection.connect();
        reader = new DatamartReader(conn);
        String json = reader.readMinTable();
        conn.close();
        return json;
    }

    public String getMaxTemperatures() throws SQLException {
        conn = createConnection.connect();
        reader = new DatamartReader(conn);
        String json = reader.readMaxTable();
        conn.close();
        return json;
    }
}
