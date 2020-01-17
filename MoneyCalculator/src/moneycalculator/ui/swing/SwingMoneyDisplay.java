package moneycalculator.ui.swing;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDisplay;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {

    private static final DecimalFormat DECIMALFORMAT = new DecimalFormat("0.00"); 
    private Money money;

    @Override
    public void display(Money money) {
        this.money = money;
        this.removeAll();
        this.add(amount());
        this.add(currency());
        this.updateUI();
    }

    private Component amount() {
        return new JLabel(String.valueOf(DECIMALFORMAT.format(money.getAmount())));
    }

    private Component currency() {
        return new JLabel(String.valueOf(money.getCurrency().getCode()));
    }
}
