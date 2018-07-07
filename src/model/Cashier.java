package model;

import java.util.ArrayList;
import java.util.Random;

public class Cashier implements Runnable {
    private Integer id;

    public Cashier(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cashier cashier = (Cashier) o;
        return id.equals(cashier.getId());
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void run() {
        CashDesk.purchasersCashDesks.put(this, new ArrayList<Purchaser>());
        System.out.println("Cashier " + this.getId() + "ready");
        System.out.println(CashDesk.purchasersCashDesks);
        while(true) {
            final Purchaser purchaser = CashDesk.getPurchaser(this);
            if (purchaser != null) {
                try {
                    synchronized (purchaser) {
                        for(Goods goods : purchaser.basket) {
                            System.out.println(id + "пробил" +goods);
                            Thread.sleep(500);
                        }
                        purchaser.notify();
                        System.out.println("Purchaser " + purchaser.id + " left from cashier " + id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
