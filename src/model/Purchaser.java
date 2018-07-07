package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString
public class Purchaser implements Runnable {
    private int id;
    private List<Goods> basket;

    public Purchaser(final int id) {
        this.id = id;
        this.basket = new ArrayList<Goods>();
        for(int i = 0; i< new Random().nextInt(10); i++) {
            this.basket.add(Goods.values()[new Random().nextInt(Goods.values().length)]);
        }
    }

    public void run() {
        CashDesk.add(this);
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
