package model;

import java.util.*;
import java.util.Map.Entry;

public class CashDesk {
    public static Map<Cashier, List<Purchaser>> purchasersCashDesks = new HashMap<Cashier, List<Purchaser>>();
    private static Integer MAX_COUNT_CASHIER = 10;
    private static Integer MIN_COUNT_CASHIER = 0;
    private int countCashier = 0;

    public CashDesk() {
        purchasersCashDesks = new HashMap<Cashier, List<Purchaser>>();
        for (int i = 0; i < MAX_COUNT_CASHIER; i++) {
            purchasersCashDesks.put(new Cashier(i), new LinkedList<Purchaser>());
        }
    }

    public static synchronized void add(final Purchaser purchaser) {
        final Entry<Cashier, List<Purchaser>> cashierWithMinQueue = Collections.min(purchasersCashDesks.entrySet(), new Comparator<Entry<Cashier, List<Purchaser>>>() {
            public int compare(Entry<Cashier, List<Purchaser>> leftValue, Entry<Cashier, List<Purchaser>> rightValue) {
                return ((Integer) leftValue.getValue().size()).compareTo(rightValue.getValue().size());
            }
        });
        cashierWithMinQueue.getValue().add(purchaser);
        System.out.println(CashDesk.purchasersCashDesks);
    }

    public static synchronized Purchaser getPurchaser(final Cashier cashier) {
        List<Purchaser> purchasers = purchasersCashDesks.get(cashier);
        if (purchasers == null) {
            purchasers = new ArrayList<Purchaser>();
        }
        for(final Purchaser purchaser : purchasers) {
            purchasers.remove(purchaser);
            purchasersCashDesks.put(cashier, purchasers);
            return purchaser;
        }
        return null;
    }

}
