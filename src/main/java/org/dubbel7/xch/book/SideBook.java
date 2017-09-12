package org.dubbel7.xch.book;

import java.util.TreeMap;

public class SideBook {

    private final boolean bidBook;


    public SideBook(boolean bidBook) {
        this.bidBook = bidBook;
    }

    public long sizeAt(int index) {
        return 0;
    }

    public long priceAt(int index) {
        return 0;
    }

    public void addOrder(String orderId, String clientId, boolean bid, long size, long price) {

    }

    public MatchResult match(String orderId, String clientId, boolean bid, long size, long price) {
        return null;
    }
}
