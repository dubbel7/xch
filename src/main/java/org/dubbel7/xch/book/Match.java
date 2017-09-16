package org.dubbel7.xch.book;

public class Match {

    private final String orderId;
    private final MatchType matchType;
    private final long matchedSize;
    private final long matchedPrice;

    public Match(String orderId, MatchType matchType, long matchedSize, long matchedPrice) {
        this.orderId = orderId;
        this.matchType = matchType;
        this.matchedSize = matchedSize;
        this.matchedPrice = matchedPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public long getMatchedSize() {
        return matchedSize;
    }

    public long getMatchedPrice() {
        return matchedPrice;
    }

    @Override
    public String toString() {
        return "Match{" +
                "orderId='" + orderId + '\'' +
                ", matchType=" + matchType +
                ", matchedSize=" + matchedSize +
                ", matchedPrice=" + matchedPrice +
                '}';
    }
}
