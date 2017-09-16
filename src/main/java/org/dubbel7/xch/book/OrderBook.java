package org.dubbel7.xch.book;


public class OrderBook {

    private final SideBook bidBook = new SideBook(true);
    private final SideBook offerBook = new SideBook(false);

    public MatchResult addOrder(String orderId, boolean bid, long size, long price) {
        MatchResult matchResult;
        if(bid) {
            matchResult = offerBook.match(orderId, bid, size, price);
            if(matchResult.getRemainingSize() > 0) {
                bidBook.addOrder(orderId, bid, size, price);
            }
        } else {
            matchResult = bidBook.match(orderId, bid, size, price);
            if(matchResult.getRemainingSize() > 0) {
                offerBook.addOrder(orderId, bid, size, price);
            }
        }

        return matchResult;
    }
}
