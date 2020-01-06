package model;

public class City {
    private final String name;
    private final String weatherDescription;
    private final double max;
    private final double min;
    private final double lat;
    private final double log;


    public City(String name, String weatherDescription, double max, double min, double lat, double log) {
        this.name = name;
        this.weatherDescription = weatherDescription;
        this.max = max;
        this.min = min;
        this.lat = lat;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getLat() {
        return lat;
    }

    public double getLog() {
        return log;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", lat=" + lat +
                ", log=" + log +
                '}';
    }
}