package moneycalculator.control;

import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;

public class CalculateCommand implements Command {

    private static final Currency EUR = new Currency("EUR", "euro", "€");
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final ExchangeRateLoader exchageRateLoader;

    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, ExchangeRateLoader exchageRateLoader) {
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchageRateLoader = exchageRateLoader;
    }

    @Override
    public String name() {
        return "Calculate";
    }

    @Override
    public void execute() {
        moneyDisplay.display(exchange(moneyDialog.get()));
    }

    private Money exchange(Money money) {
        return new Money(money.getAmount() / rateOf(money.getCurrency()), EUR);
    }

    private double rateOf(Currency currency) {
        return exchageRateLoader.load(currency, EUR).getAmount();
    }
}
