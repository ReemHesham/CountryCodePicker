package reemhesham.countrycodepicker;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Reem on 9/28/2015.
 */
public class Utils {


    public static String SELECTED_COUNTRY_CODE = "selectedCountryCode ";
    public static String SELECTED_COUNTRY_ISO_CODE = "selectedCountryISOCode ";
    public static String SELECTED_COUNTRY_NAME = "selectedCountryName ";

    public static final String[] countryISOCodes = {"AC", "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ",
            "AR", "AS", "AT", "AU", "AW", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BM", "BN", "BO",
            "BR", "BS", "BT", "BW", "BY", "BZ", "CA", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR",
            "CU", "CV", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO",
            "FR", "GA", "GD", "GE", "GF", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GT", "GU", "GW", "GY", "HK", "HN", "HR", "HT",
            "HU", "ID", "IE", "IL", "IN", "IO", "IQ", "IR", "IS", "IT", "JA", "JM", "JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR",
            "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MG", "MH", "MK", "ML", "MM",
            "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NG", "NI", "NL", "NO", "NP", "NR",
            "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW",
            "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SK", "SL", "SM", "SN", "SO", "SR", "ST", "SV", "SX", "SY", "SZ", "TC", "TD", "TG",
            "TH", "TJ", "TK", "TM", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UK", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI",
            "VN", "VU", "WF", "WS", "XT", "YE", "ZA", "ZM", "ZW"};


    public static final String[] countryCodes = {"247", "376", "971", "93", "1", "1", "355", "374", "599", "244", "672",
            "54", "1", "43", "61", "297", "994", "387", "1", "880", "32", "226", "359", "973", "257", "229", "1", "673",
            "591", "55", "1", "975", "267", "375", "501", "1", "243", "236", "242", "41", "225", "682", "56", "237", "86",
            "57", "506", "53", "238", "357", "420", "49", "253", "45", "1", "1", "213", "593", "372", "20", "291", "34", "251", "358",
            "679", "500", "691", "298", "33", "241", "1", "995", "594", "233", "350", "299", "220", "224", "590", "240", "30", "502",
            "1", "245", "592", "852", "504", "385", "509", "36", "62", "353", "972", "91", "246", "964", "98", "354", "39", "81", "1", "962",
            "81", "254", "996", "855", "686", "269", "1", "850", "82", "965", "1", "7", "856", "961", "1", "423", "94", "231", "266", "370",
            "352", "371", "218", "212", "377", "373", "382", "261", "692", "389", "223", "95", "976", "853", "1", "596", "222", "1", "356",
            "230", "960", "265", "52", "60", "258", "264", "687", "227", "234", "505", "31", "47", "977", "674", "683", "64", "968", "507",
            "51", "689", "675", "63", "92", "48", "508", "1", "970", "351", "680", "595", "974", "262", "40", "381", "7", "250", "966", "677",
            "248", "249", "46", "65", "290", "386", "421", "232", "378", "221", "252", "597", "239", "503", "1", "963", "268", "1", "235",
            "228", "66", "992", "690", "993", "216", "676", "90", "1", "688", "886", "255", "380", "256", "44", "1", "598", "998", "379", "1",
            "58", "1", "1", "84", "678", "681", "685", "800", "967", "27", "260", "263"};

    static ArrayList<String> getCountriesNameArrayList() {
        ArrayList<String> countriesName = new ArrayList();
        for (int i = 0; i < countryISOCodes.length; i++) {
            Locale loc = new Locale("", countryISOCodes[i]);
            String country = loc.getDisplayCountry();
            countriesName.add(country);
        }
        return countriesName;
    }

    static ArrayList<CountryCodes> getCountriesCodeArrayList(ArrayList<String> countriesName) {
        ArrayList<CountryCodes> countriesCodeList = new ArrayList<>();
        for (int i = 0; i < Utils.countryISOCodes.length; i++)

        {
            CountryCodes countryCodes = new CountryCodes(Utils.countryISOCodes[i],
                    countriesName.get(i), Utils.countryCodes[i]);
            countriesCodeList.add(countryCodes);
        }
        return countriesCodeList;
    }
}
