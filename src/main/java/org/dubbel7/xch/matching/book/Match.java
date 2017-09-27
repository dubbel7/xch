package org.dubbel7.xch.matching.book;

public class Match {

    private final String orderId;
    private final MatchType matchType;
    private final long matchedSize;
    private final long matchedPrice;

    public Match(String orderId, long matchedSize, long matchedPrice, MatchType matchType) {
        this.orderId = orderId;
        this.matchedSize = matchedSize;
        this.matchedPrice = matchedPrice;
        this.matchType = matchType;
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
