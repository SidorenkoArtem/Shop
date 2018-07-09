package model;

import lombok.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ToString
@Setter
@Getter
public class Cashier implements Runnable {

    private Integer id;
    private Integer x;
    private Integer y;
    private JPanel panel;

    public Cashier(final Integer id, final Integer x, final Integer y, final JPanel panel) {
        this.id = id;
        this.panel = panel;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashier cashier = (Cashier) o;
        return this.id.equals(cashier.id);
    }

    @Override
    public int hashCode(){
        return this.id;

    }

    public void run() {
        synchronized (CashDesk.purchasersCashDesks) {
            CashDesk.purchasersCashDesks.put(this, new ArrayList<Purchaser>());
        }
        System.out.println("Cashier " + this.getId() + "ready");
        System.out.println(CashDesk.purchasersCashDesks);
        while(true) {
            final Purchaser purchaser = CashDesk.getPurchaser(this, panel.getGraphics(), panel.getBackground());
            if (purchaser != null) {
                try {
                    paint(panel.getGraphics(), Color.BLACK);
                    synchronized (purchaser) {
                        for(Goods goods : purchaser.getBasket()) {
                            Thread.sleep(1000);//Оценка товара
                        }
                        purchaser.notify();
                        System.out.println("Purchaser " + purchaser.getId() + " left from cashier " + id);
                    }
                    paint(panel.getGraphics(), Color.WHITE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void paint(Graphics g, Color color) {
        if (g != null) {
            g.setColor(color);
            g.fillRect(20, this.y, 40, 20);
        }
    }
}
