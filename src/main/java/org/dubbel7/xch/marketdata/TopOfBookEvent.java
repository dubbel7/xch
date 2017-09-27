package org.dubbel7.xch.marketdata;

public class TopOfBookEvent {

    private String instrumentId;

    private long bidSize;
    private long bidPrice;
    private long offerSize;
    private long offerPrice;

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public void setBidSize(long bidSize) {
        this.bidSize = bidSize;
    }

    public void setBidPrice(long bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void setOfferSize(long offerSize) {
        this.offerSize = offerSize;
    }

    public void setOfferPrice(long offerPrice) {
        this.offerPrice = offerPrice;
    }

    @Override
    public String toString() {
        return "TopOfBookEvent{" +
                "instrumentId='" + instrumentId + '\'' +
                ", bidSize=" + bidSize +
                ", bidPrice=" + bidPrice +
                ", offerSize=" + offerSize +
                ", offerPrice=" + offerPrice +
                '}';
    }
}
