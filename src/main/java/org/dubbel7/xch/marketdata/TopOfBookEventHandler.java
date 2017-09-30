package org.dubbel7.xch.marketdata;

import com.lmax.disruptor.EventHandler;

public class TopOfBookEventHandler implements EventHandler<TopOfBookEvent> {

    @Override
    public void onEvent(TopOfBookEvent topOfBookEvent, long l, boolean b) throws Exception {
        System.out.println("Received! " + topOfBookEvent);
    }
}
