package org.dubbel7.xch.disruptor;

import com.lmax.disruptor.EventHandler;

public class OrderEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Received order event " + orderEvent);
    }
}
