package model;

import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

public class CashDesk {
    public static Map<Cashier, List<Purchaser>> purchasersCashDesks = new HashMap<Cashier, List<Purchaser>>();

    public static synchronized void add(final Purchaser purchaser, Graphics g) {
        final Entry<Cashier, List<Purchaser>> cashierWithMinQueue = Collections.min(purchasersCashDesks.entrySet(), new Comparator<Entry<Cashier, List<Purchaser>>>() {
            public int compare(Entry<Cashier, List<Purchaser>> leftValue, Entry<Cashier, List<Purchaser>> rightValue) {
                return ((Integer) leftValue.getValue().size()).compareTo(rightValue.getValue().size());
            }
        });
        cashierWithMinQueue.getValue().add(purchaser);
        paint(g,cashierWithMinQueue.getValue().size(), cashierWithMinQueue.getKey().getY());
        System.out.println(CashDesk.purchasersCashDesks);
    }

    public static synchronized Purchaser getPurchaser(final Cashier cashier, Graphics g) {
        List<Purchaser> purchasers = purchasersCashDesks.get(cashier);
        if (purchasers == null) {
            purchasers = new ArrayList<Purchaser>();
        }
        for(final Purchaser purchaser : purchasers) {
            System.out.println(purchasers.size());
            purchasers.remove(purchaser);
            purchasersCashDesks.put(cashier, purchasers);
            paint(g, purchasers.size(), cashier.getY());
            return purchaser;
        }
        return null;
    }

    public synchronized static void paint(Graphics g, Integer count, Integer y) {
        g.setColor(Color.WHITE);
        g.fillRect(23, y, (count + 1)*23, 30);
        for(int i = 1; i< count + 1; i++) {
            g.setColor(Color.BLACK);
            g.fillRect(i *23, y, 20, 20);
        }

    }

}
