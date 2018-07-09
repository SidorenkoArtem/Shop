package model;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

public class CashDesk {
    public static Map<Cashier, List<Purchaser>> purchasersCashDesks = new HashMap<Cashier, List<Purchaser>>();
    public static synchronized void add(final Purchaser purchaser, Graphics g, final Color color) {
        final Entry<Cashier, List<Purchaser>> cashierWithMinQueue = Collections.min(purchasersCashDesks.entrySet(), new Comparator<Entry<Cashier, List<Purchaser>>>() {
            public int compare(Entry<Cashier, List<Purchaser>> leftValue, Entry<Cashier, List<Purchaser>> rightValue) {
                return ((Integer) leftValue.getValue().size()).compareTo(rightValue.getValue().size());
            }
        });
        cashierWithMinQueue.getValue().add(purchaser);
        paint(g, cashierWithMinQueue.getValue().size(), cashierWithMinQueue.getKey().getY(), color);
        System.out.println(CashDesk.purchasersCashDesks);
    }

    public static synchronized Purchaser getPurchaser(final Cashier cashier, Graphics g, final Color color) {
        List<Purchaser> purchasers = purchasersCashDesks.get(cashier);
        if (purchasers == null) {
            purchasers = new ArrayList<Purchaser>();
        }
        for (final Purchaser purchaser : purchasers) {
            System.out.println(purchasers.size());
            purchasers.remove(purchaser);
            purchasersCashDesks.put(cashier, purchasers);
            paint(g, purchasers.size(), cashier.getY(), color);
            return purchaser;
        }
        return null;
    }

    public synchronized static void paint(final Graphics g, final Integer count, final Integer y, final Color color) {
        if (g != null) {
            g.setColor(color);
            g.fillRect(80, y, 60 + (count + 1) * 43, 30);
            for (int i = 1; i < count + 1; i++) {
                g.setColor(Color.BLACK);
                g.fillRect(60 + i * 43, y, 40, 20);
            }
        }

    }

}
