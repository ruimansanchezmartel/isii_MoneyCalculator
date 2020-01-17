package moneycalculator.persistence.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateLoader;

public class RestExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from, to, read(from.getCode(), to.getCode()));
        } catch (IOException ex) {
            return null;
        }
    }

    private double read(String from, String to) throws IOException {
        String line = read(new URL("https://api.exchangeratesapi.io/latest?symbols=" + from));
        return Double.parseDouble(line.substring(line.indexOf(from) + 5, line.indexOf("}")));
    }

    private String read(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[1024];
        int length = inputStream.read(buffer);
        return new String(buffer, 0, length);
    }
}
