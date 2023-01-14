package aemet;

import java.sql.Connection;
import java.sql.SQLException;


public class DatamartInsert {

    private Connection conn;

    public DatamartInsert(Connection conn) {
        this.conn = conn;
    }


    public void insertStatementOfMin(Weather weather) throws SQLException {
        DmlTranslator dmlTranslator = new DmlTranslator(conn);
        dmlTranslator.MinToDml(weather.getDate().substring(0, 10), weather.getDate().substring(11, 18), weather.getPlace(), weather.getStation(), weather.getTmin());
    }

    public void insertStatementOfMax(Weather weather) throws SQLException {
        DmlTranslator dmlTranslator = new DmlTranslator(conn);
        dmlTranslator.MaxToDml(weather.getDate().substring(0, 10), weather.getDate().substring(11, 18), weather.getPlace(), weather.getStation(), weather.getTmax());
    }
}
