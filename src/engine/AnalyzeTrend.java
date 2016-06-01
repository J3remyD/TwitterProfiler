package engine;

public class AnalyzeTrend {
    private final String id;
    private final String countryName;
    private final String city;

    public AnalyzeTrend(String id, String countryName, String city) {
        this.id = id;
        this.countryName = countryName;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCity() {
        return city;
    }

}
