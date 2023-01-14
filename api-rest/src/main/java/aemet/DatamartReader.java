package aemet;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatamartReader {
    private Connection connection;

    private Gson gson = new Gson();

    public DatamartReader(Connection connection) {
        this.connection = connection;
    }

    public String readMaxTable() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select * from MaxTemperatures");
        List<WeatherDB> weathers = new ArrayList<>();

        while(rs.next()){
            WeatherDB weatherDB = new WeatherDB(rs.getString("DATE"), rs.getString("TIME"), rs.getString("PLACE"), rs.getString("STATION"), rs.getDouble("VALUE"));
            weathers.add(weatherDB);
        }


        return gson.toJson(weathers);
    }

    public String readMinTable() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select * from MinTemperatures");
        List<WeatherDB> weathers = new ArrayList<>();

        while(rs.next()){
            WeatherDB weatherDB = new WeatherDB(rs.getString("DATE"), rs.getString("TIME"), rs.getString("PLACE"), rs.getString("STATION"), rs.getDouble("VALUE"));
            weathers.add(weatherDB);
        }


        return gson.toJson(weathers);
    }
}
