package org.dubbel7.xch.marketdata;

public interface TopOfBookCallback {

    void onTopOfBook(String instrumentId, long bigPrice, long bidSize, long offerPrice, long offerSize);
}
