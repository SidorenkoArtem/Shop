package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString
public class Purchaser implements Runnable {
    private int id;
    private List<Goods> basket;
    private JPanel panel;

    public Purchaser(final int id, final JPanel panel) {
        this.id = id;
        this.panel = panel;
        this.basket = new ArrayList<Goods>();
        for(int i = 0; i< new Random().nextInt(10); i++) {
            this.basket.add(Goods.values()[new Random().nextInt(Goods.values().length)]);
        }
    }

    public void run() {
        CashDesk.add(this, panel.getGraphics(), panel.getBackground());
        System.out.println("Purchaser ready " + this.id);
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
