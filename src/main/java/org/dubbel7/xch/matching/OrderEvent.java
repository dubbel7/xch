package org.dubbel7.xch.matching;


public class OrderEvent {

    public enum Side{BID,OFFER}

    private String orderId;
    private String clientId;
    private String instrumentId;

    private Side side;
    private long size;
    private long price;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setSize(long size) {
        this.size = size;
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
