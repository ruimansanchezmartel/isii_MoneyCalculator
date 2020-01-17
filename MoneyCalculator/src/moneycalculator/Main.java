package moneycalculator;

import moneycalculator.control.CalculateCommand;
import moneycalculator.persistence.CurrencyListLoader;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.persistence.files.FileCurrencyListLoader;
import moneycalculator.persistence.rest.RestExchangeRateLoader;

public class Main {

    public static void main(String[] args) {
        CurrencyListLoader currencyListLoader = new FileCurrencyListLoader("currencies.csv");
        ExchangeRateLoader exchangeRateLoader = new RestExchangeRateLoader();
        MainFrame mainFrame = new MainFrame(currencyListLoader.currencies());
        mainFrame.add(new CalculateCommand(mainFrame.getMoneyDialog(), mainFrame.getMoneyDisplay(), exchangeRateLoader));
    }
}
