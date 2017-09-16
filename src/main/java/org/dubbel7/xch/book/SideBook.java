package org.dubbel7.xch.book;


import java.util.ArrayList;
import java.util.List;

public class SideBook {

    private final boolean bidBook;

    private final List<BookEntry> entries = new ArrayList<>();

    public SideBook(boolean bidBook) {
        this.bidBook = bidBook;
    }


    public long sizeAt(int index) {
        return 0;
    }

    public long priceAt(int index) {
        return 0;
    }

    public void addOrder(String orderId, boolean bid, long size, long price) {

    }

    public MatchResult match(String orderId, boolean bid, long size, long price) {
        return null;
    }
}
