package engine;

public class AnalyzeTrend {
    private final String countryName;
    private final String city;
    private final int id;

    public AnalyzeTrend(String countryName, String city, int id) {
        this.countryName = countryName;
        this.city = city;
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }
}
