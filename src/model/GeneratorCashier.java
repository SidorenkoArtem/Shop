package model;

import lombok.Data;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class GeneratorCashier {

    private List<Cashier> cashiers;
    private int countCashiers;

    public GeneratorCashier(final int countCashiers, JPanel panel) {
        this.cashiers = new ArrayList<Cashier>();
        this.countCashiers = countCashiers;
        for (int i = 1; i < countCashiers + 1; i++) {
            final Cashier cashier = new Cashier(i, 20, 50 * i, panel);
            this.cashiers.add(cashier);
        }
    }

    public void runCashiers() {
        for (Cashier cashier : this.cashiers) {
            new Thread(cashier).start();
        }
    }
}
