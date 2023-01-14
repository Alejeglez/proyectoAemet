package aemet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class FeederTask {

    private String json;
    private Gson gson = new Gson();


    public FeederTask(String json) {
        this.json = json;
    }

    public List<Weather> filterToList(){

        JsonArray array = gson.fromJson(json, JsonArray.class);
        List<Weather> weatherList = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            JsonObject jO = array.get(i).getAsJsonObject();

            try {
                Weather weather = new Weather(jO.get("fint").getAsString(), jO.get("idema").getAsString(), jO.get("ubi").getAsString(), jO.get("tamax").getAsDouble(), jO.get("tamin").getAsDouble(), jO.get("ta").getAsDouble());
                weatherList.add(weather);
            }
            catch (NullPointerException e){

            }

        }
        return weatherList;
    }
}
