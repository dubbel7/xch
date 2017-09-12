package org.dubbel7.xch;

public class Order {

    private final String orderId;
    private final String clientId;
    private final String instrumentId;
    private final boolean bid;
    private final long size;
    private final long price;

    public Order(String orderId, String clientId, String instrumentId, boolean bid, long size, long price) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.instrumentId = instrumentId;
        this.bid = bid;
        this.size = size;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public boolean isBid() {
        return bid;
    }

    public long getSize() {
        return size;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", instrumentId='" + instrumentId + '\'' +
                ", bid=" + bid +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
