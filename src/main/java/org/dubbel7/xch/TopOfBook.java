package org.dubbel7.xch;

public class TopOfBook {

    private final String instrument;
    private final long bid;
    private final long ask;


    public TopOfBook(String instrument, long bid, long ask) {
        this.instrument = instrument;
        this.bid = bid;
        this.ask = ask;
    }

    public String getInstrument() {
        return instrument;
    }

    public long getBid() {
        return bid;
    }

    public long getAsk() {
        return ask;
    }

    @Override
    public String toString() {
        return "TopOfBook{" +
                "instrument='" + instrument + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
