package aemet;

import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.text.ParseException;

public interface WebService {
    void launch();
    String getMinTemperatures(Request request, Response response) throws SQLException, ParseException;
    String getMaxTemperatures(Request request, Response response) throws SQLException, ParseException;
}

