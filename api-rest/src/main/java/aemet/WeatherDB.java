package aemet;

public class WeatherDB {

    private String date;
    private String time;
    private String place;
    private String station;
    private double value;

    public WeatherDB(String date, String time, String place, String station, double value) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.station = station;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getStation() {
        return station;
    }

    public double getValue() {
        return value;
    }
}
