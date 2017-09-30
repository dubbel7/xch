package org.dubbel7.xch.marketdata;

public interface TopOfBookCallback {

    void onTopOfBook(String instrumentId, long bidPrice, long bidSize, long offerPrice, long offerSize);
}
