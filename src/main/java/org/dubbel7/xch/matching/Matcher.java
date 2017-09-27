package org.dubbel7.xch.matching;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.dubbel7.xch.Order;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Matcher {

    private final Disruptor<OrderEvent> disruptor;

    public static void translate(OrderEvent event, long sequence, Order order) {
        event.setOrderId(order.getOrderId());
        event.setClientId(order.getClientId());
        event.setInstrumentId(order.getInstrumentId());
        event.setSide(order.isBid() ? OrderEvent.Side.BID : OrderEvent.Side.OFFER);
        event.setPrice(order.getPrice());
        event.setSize(order.getSize());
    }


    public Matcher() {
        Executor executor = Executors.newCachedThreadPool();
        //must be power of 2
        int bufferSize = 1024;
        //TODO use non deprecated constructor
        disruptor = new Disruptor<>(OrderEvent::new, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new OrderEventHandler());
        disruptor.start();
    }

    public void onOrderReceived(Order order) {
        disruptor.getRingBuffer().publishEvent(Matcher::translate, order);
    }
}
