package model;

import lombok.Data;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class GeneratorPurchaser implements Runnable {
    private int countPurchasers;
    private List<Purchaser> purchasers;

    public GeneratorPurchaser(final int countPurchaser, final JPanel panel) {
        this.countPurchasers = countPurchaser;
        this.purchasers = new ArrayList<Purchaser>();
        for (int i = 0; i < countPurchaser; i++) {
            this.purchasers.add(new Purchaser(i, panel));
        }
    }

    public void runPurchaser() {
        System.out.println("Purchaser: " + purchasers);
        for(final Purchaser purchaser : this.purchasers) {
            new Thread(purchaser).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        runPurchaser();
    }
}
