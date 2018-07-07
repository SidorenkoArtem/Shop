package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GeneratorPurchaser {
    private int countPurchasers;
    private List<Purchaser> purchasers;

    public GeneratorPurchaser(final int countPurchaser) {
        this.countPurchasers = countPurchaser;
        this.purchasers = new ArrayList<Purchaser>();
        for (int i = 0; i < countPurchaser; i++) {
            this.purchasers.add(new Purchaser(i));
        }
    }

    public void runPurchaser() {
        System.out.println("Purchaser: " + purchasers);
        for(final Purchaser purchaser : this.purchasers) {
            new Thread(purchaser).start();
        }
    }
}
