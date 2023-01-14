package aemet;

public class Weather {
    private String date;
    private String station;
    private String place;
    private double tmax;
    private double tmin;
    private double temperature;


    public Weather(String date, String station, String place, double tmax, double tmin, double temperature) {
        this.date = date;
        this.station = station;
        this.place = place;
        this.tmax = tmax;
        this.tmin = tmin;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public String getStation() {
        return station;
    }

    public String getPlace() {
        return place;
    }

    public double getTmax() {
        return tmax;
    }

    public double getTmin() {
        return tmin;
    }

    public double getTemperature() {
        return temperature;
    }
}
