package org.dubbel7.xch.book;

public class Match {

    public enum Type {FILL, PARTIAL_FILL}

    private final String orderId;
    private final Type type;
    private final long matchedSize;
    private final long matchedPrice;

    public Match(String orderId, Type type, long matchedSize, long matchedPrice) {
        this.orderId = orderId;
        this.type = type;
        this.matchedSize = matchedSize;
        this.matchedPrice = matchedPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public Type getType() {
        return type;
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
                ", type=" + type +
                ", matchedSize=" + matchedSize +
                ", matchedPrice=" + matchedPrice +
                '}';
    }
}
