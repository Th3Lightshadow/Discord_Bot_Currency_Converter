package auxiliary;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SuppressWarnings("ConstantConditions")
public class MoneyConvertion {
    static final String[] currencyName = {"USD", "JPY", "BGN", "CZK", "DKK", "GBP", "HUF", "PLN", "RON", "SEK", "CHF", "ISK", "NOK", "HRK", "RUB", "TRY", "AUD", "BRL", "CAD", "CNY", "HKD", "IDR", "ILS", "INR", "KRW", "MXN", "MYR", "NZD", "PHP", "SGD", "THB", "ZAR", "EUR"};

    public static float convert(float input, String from, String to) {
        float[] rate = null;
        try {
            rate = RateImport.getRate();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        float temp = input;
        int fromInt = findIndex(currencyName, from);
        if (fromInt == -1) {
            return -1;
        }
        int toInt = findIndex(currencyName, to);
        if (fromInt == -1) {
            return -1;
        }

        if (!(from.equals("EUR"))) {
            temp = input / rate[fromInt];
        }
        if (!(to.equals("EUR")))
        {
            temp = temp * rate[toInt];
        }
        return temp;
    }

    public static int findIndex(String[] currencyName, String currency) {
        for (int i = 0; i < currencyName.length; i++) {
            if (currencyName[i].equals(currency)) {
                return i;
            }
        }
        return -1;
    }
}
