package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private final Currency[] currencies;
    private String amount;
    private Currency currency;

    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.add(amount());
        this.add(currency());
    }

    @Override
    public Money get() {
        return new Money(Double.parseDouble(amount), currency);
    }

    private Component amount() {
        JTextField textField = new JTextField("100");
        textField.setColumns(10);
        textField.getDocument().addDocumentListener(amountChaged());
        amount = textField.getText();
        return textField;
    }

    private Component currency() {
        JComboBox comboBox = new JComboBox(currencies);
        comboBox.addItemListener(currencyChanged());
        currency = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }

    private ItemListener currencyChanged() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) return;
                currency = (Currency) e.getItem();
            }
        };
    }

    private DocumentListener amountChaged() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                amountChanged(e.getDocument());
            }

            private void amountChanged(Document document) {
                try {
                    amount = document.getText(0, document.getLength());
                } catch (BadLocationException ex) {
                    
                }
            }
        };
    }
}
