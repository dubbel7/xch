package org.dubbel7.xch.matching;


public class OrderEvent {

    public enum Side{BID,OFFER}

    private String orderId;
    private String clientId;
    private String instrumentId;

    private Side side;
    private long size;
    private long price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "orderId='" + orderId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", instrumentId='" + instrumentId + '\'' +
                ", side=" + side +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
