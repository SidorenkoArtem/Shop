package model;

import java.util.ArrayList;
import java.util.List;

public class GeneratorCashier {

    private List<Cashier> cashiers;
    private final int countCashiers;

    public GeneratorCashier(final int countCashiers) {
        this.cashiers = new ArrayList<Cashier>();
        this.countCashiers = countCashiers;
        for (int i = 0; i < countCashiers; i++) {
            final Cashier cashier = new Cashier(i);
            this.cashiers.add(cashier);
        }
    }

    public void runCashiers() {
        for (Cashier cashier : this.cashiers) {
            new Thread(cashier).start();
        }
    }
}
