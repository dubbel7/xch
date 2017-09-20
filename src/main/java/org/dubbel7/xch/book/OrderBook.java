package org.dubbel7.xch.book;


public class OrderBook {

    private final SideBook bidBook = new SideBook(true);
    private final SideBook offerBook = new SideBook(false);

    public long addOrder(final String orderId,
                                final boolean bid,
                                final long size,
                                final long price,
                                final MatchCallback matchCallback) {
        long remainingSize;
        if (bid) {
            remainingSize = offerBook.match(orderId, size, price, matchCallback);
            if (remainingSize > 0) {
                bidBook.addOrder(orderId, size, price);
            }
        } else {
            remainingSize = bidBook.match(orderId, size, price, matchCallback);
            if (remainingSize > 0) {
                offerBook.addOrder(orderId, size, price);
            }
        }

        return remainingSize;
    }
}
