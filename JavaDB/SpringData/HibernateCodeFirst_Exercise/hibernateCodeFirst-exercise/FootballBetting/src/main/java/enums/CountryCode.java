package enums;

public enum CountryCode {
    BUL("Bulgaria"),
    USA("United States of America"),
    GER("Germany"),
    FRA("France"),
    ITA("Italy");

    public String countryName;

    private CountryCode(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

}
