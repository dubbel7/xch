package org.dubbel7.xch;

public class Match {

    private final String orderId;
    private final long matchedSize;
    private final long matchedPrice;

    public Match(String orderId, long matchedSize, long matchedPrice) {
        this.orderId = orderId;
        this.matchedSize = matchedSize;
        this.matchedPrice = matchedPrice;
    }
}
