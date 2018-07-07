package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Purchaser implements Runnable {
    public int id;

    public List<Goods> basket;

    public Purchaser(final int id) {
        this.id = id;
        this.basket = new ArrayList<Goods>();
        for(int i = 0; i< new Random().nextInt(10); i++) {
            this.basket.add(Goods.values()[new Random().nextInt(Goods.values().length)]);
        }
    }

    public Purchaser(final List<Goods> goods) {
        this.basket = goods;
    }

    public List<Goods> getBasket() {
        return basket;
    }

    public void setBasket(List<Goods> basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Purchaser{" +
                "basket=" + basket +
                '}';
    }

    public void run() {
        CashDesk.add(this);
        System.out.println("Purchaser ready" + this.id);

        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
