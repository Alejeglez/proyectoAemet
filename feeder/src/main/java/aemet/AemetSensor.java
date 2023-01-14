package aemet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AemetSensor implements Sensor{

    private String apiKey;
    private String url="https://opendata.aemet.es/opendata/api/observacion/convencional/todas";

    private Gson gson = new Gson();

    public AemetSensor(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getData() {
        try {

            JSONObject jo = new JSONObject(getFirstUrl(url));
            JsonArray arrayResponse = gson.fromJson(getMeasures(jo), JsonArray.class);
            return filterList(fromJsonArrayToList(arrayResponse));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String filterList(ArrayList<JsonObject> list){
        List<JsonObject> listFiltered = list.stream()
                .filter(jO-> jO.get("lon").getAsFloat() > -16)
                .filter(jO-> jO.get("lon").getAsFloat() < -15)
                .filter(jO-> jO.get("lat").getAsFloat() > 27.5)
                .filter(jO-> jO.get("lat").getAsFloat() < 28.4)
                .collect(Collectors.toList());

        return gson.toJson(listFiltered);
    }

    private String getFirstUrl(String url) throws IOException {
        String response = Jsoup.connect(url)
                .validateTLSCertificates(false)
                .timeout(6000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .execute().body();
        return response;
    }

    private String getMeasures(JSONObject jo) throws IOException {
        String responseWeather = Jsoup.connect(jo.getString("datos"))
                .validateTLSCertificates(false)
                .timeout(12000)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
        return responseWeather;
    }


    private ArrayList<JsonObject> fromJsonArrayToList(JsonArray arrayResponse){
        ArrayList<JsonObject> listResponse = new ArrayList<JsonObject>();
        if (arrayResponse != null) {
            for (int i=0;i<arrayResponse.size();i++){
                listResponse.add(arrayResponse.get(i).getAsJsonObject());
            }
        }
        return listResponse;
    }
}
