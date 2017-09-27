package org.dubbel7.xch.matching.book;


public class OrderBook {

    private final String instrumentId;


    private final SideBook bidBook = new SideBook(true);
    private final SideBook offerBook = new SideBook(false);

    public OrderBook(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

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

    public long getTopBidPrice() {
        return bidBook.priceAt(0);
    }

    public long getTopBidSize() {
        return bidBook.sizeAt(0);
    }

    public long getTopOfferPrice() {
        return offerBook.priceAt(0);
    }

    public long getTopOfferSize() {
        return offerBook.sizeAt(0);
    }
}
