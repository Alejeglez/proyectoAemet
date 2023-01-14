package aemet;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.get;

public class ApiAemet implements WebService{


    private Controller controller;
    private Gson gson = new Gson();


    public ApiAemet() {

        this.controller = new Controller();
    }

    public void launch(){
        get("/v1/places/with-max-temperature", ((request, response) -> getMaxTemperatures(request, response)));
        get("/v1/places/with-min-temperature", ((request, response) -> getMinTemperatures(request, response)));
    }


    @Override
    public String getMinTemperatures(Request request, Response response) throws SQLException, ParseException {
        response.header("content-type", "application/json");
        String dateFromParam = request.queryParams("from");
        String dateToParam = request.queryParams("to");
        if(dateFromParam == null || dateToParam == null){return controller.getMinTemperatures();}
        return filterByDate(dateToParam, dateFromParam, controller.getMinTemperatures());
    }

    private String filterByDate(String dateToParam, String dateFromParam, String json) throws ParseException {
        Date dateFrom =new SimpleDateFormat("yyyy-MM-dd").parse(dateFromParam);
        Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateToParam);

        List<WeatherDB> weathersFilter  = new ArrayList<>();
        WeatherDB[] weatherDBS = gson.fromJson(json, WeatherDB[].class);
        for(int i = 0; i < weatherDBS.length; i++){
            Date dateWeather = new SimpleDateFormat("yyyy-MM-dd").parse(weatherDBS[i].getDate());

            if((dateWeather.compareTo(dateFrom) > 0 && dateWeather.compareTo(dateTo) < 0) || dateWeather.compareTo(dateFrom) == 0 ||dateWeather.compareTo(dateTo) == 0){
                weathersFilter.add(weatherDBS[i]);
            }
        }
        return  gson.toJson(weathersFilter);
    }

    @Override
    public String getMaxTemperatures(Request request, Response response) throws SQLException, ParseException {
        response.header("content-type", "application/json");
        String dateFromParam = request.queryParams("from");
        String dateToParam = request.queryParams("to");
        if(dateFromParam == null || dateToParam == null){return controller.getMaxTemperatures();}
        return filterByDate(dateToParam, dateFromParam, controller.getMaxTemperatures());
    }
}
