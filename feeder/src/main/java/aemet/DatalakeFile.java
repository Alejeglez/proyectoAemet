package aemet;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class DatalakeFile implements Datalake{

    private String path;

    private String defPath;

    private Gson gson = new Gson();
    private String date = "";

    public DatalakeFile() {
        this.path = System.getProperty("user.dir") + "\\datalake";
    }

    @Override
    public void save(List<Weather> weathers) throws IOException {


        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        for (Weather weather : weathers){
            if(!date.equals(weather.getDate().substring(0,4) + weather.getDate().substring(5,7) + weather.getDate().substring(8,10))){
                date = weather.getDate().substring(0,4) + weather.getDate().substring(5,7) + weather.getDate().substring(8,10);
                defPath = path + "\\" + date + ".events";
                File events = new File(defPath);
                events.createNewFile();
            }

            String json = gson.toJson(weather);

            if(!Files.lines(Paths.get(defPath)).anyMatch(a -> a.contains(json))) {

                Files.write(Paths.get(defPath), (json + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        }





    }

    @Override
    public List<Weather> read(File datalake) throws IOException {
        Gson gson = new Gson();
        String content = Files.readString(datalake.toPath());
        System.out.println(content);
        List<Weather> weathers = gson.fromJson(content, List.class);
        return weathers;
    }
}
