package model;

import lombok.*;

import java.util.ArrayList;

@ToString
@EqualsAndHashCode
public class Cashier implements Runnable {
    @Setter
    @Getter
    private Integer id;

    public Cashier(final Integer id) {
        this.id = id;
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
                        for(Goods goods : purchaser.getBasket()) {
                            System.out.println(id + "пробил" +goods);
                            Thread.sleep(500);
                        }
                        purchaser.notify();
                        System.out.println("Purchaser " + purchaser.getId() + " left from cashier " + id);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
