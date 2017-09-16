package org.dubbel7.xch.book;

public interface MatchCallback {

    void onMatch(String orderId, long size, long price, MatchType type);
}
