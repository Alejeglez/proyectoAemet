package aemet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DmlTranslator {

    private Connection conn;


    public DmlTranslator(Connection conn) {

        this.conn = conn;
    }

    public void MinToDml(String date, String time, String place, String station, double value) throws SQLException {

        String DmlStatement = "INSERT INTO MinTemperatures VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(DmlStatement);
        pstmt.setString(1, date);
        pstmt.setString(2, time);
        pstmt.setString(3, place);
        pstmt.setString(4, station);
        pstmt.setDouble(5, value);

        pstmt.executeUpdate();

    }

    public void MaxToDml(String date, String time, String place, String station, double value) throws SQLException {

        String DmlStatement = "INSERT INTO MaxTemperatures VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(DmlStatement);
        pstmt.setString(1, date);
        pstmt.setString(2, time);
        pstmt.setString(3, place);
        pstmt.setString(4, station);
        pstmt.setDouble(5, value);

        pstmt.executeUpdate();

    }
}
