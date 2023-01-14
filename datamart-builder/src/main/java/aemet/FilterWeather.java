package aemet;

import java.util.Comparator;
import java.util.List;

public class FilterWeather {

    private List<Weather> weathers;

    public FilterWeather(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Weather minWeather(){
        Weather weatherMin = weathers.stream()
                .min(Comparator.comparing(Weather::getTmin)).orElse(null);
        return weatherMin;
    }

    public Weather maxWeather(){
        Weather weatherMax = weathers.stream()
                .max(Comparator.comparing(Weather::getTmax)).orElse(null);
        return weatherMax;
    }
}
