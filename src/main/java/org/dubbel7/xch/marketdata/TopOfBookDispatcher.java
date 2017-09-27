package org.dubbel7.xch.marketdata;

public class TopOfBookDispatcher implements TopOfBookCallback{
    @Override
    public void onTopOfBook(String instrumentId, long bigPrice, long bidSize, long offerPrice, long offerSize) {
        System.out.println("top of book!");
    }
}
