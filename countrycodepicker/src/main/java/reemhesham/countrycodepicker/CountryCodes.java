package reemhesham.countrycodepicker;

/**
 * Created by Reem on 8/4/2015.
 */
public class CountryCodes {
    String countryISOCode;
    String countryName;
    String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public CountryCodes(String flagCode, String countryName,String countryCode) {
        this.countryISOCode = flagCode;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
