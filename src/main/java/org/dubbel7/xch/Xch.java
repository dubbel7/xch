package org.dubbel7.xch;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.dubbel7.xch.disruptor.OrderEvent;
import org.dubbel7.xch.disruptor.OrderEventHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Xch {


    public static void translate(OrderEvent event, long sequence, Order order) {
        event.setOrderId(order.getOrderId());
        event.setClientId(order.getClientId());
        event.setInstrumentId(order.getInstrumentId());
        event.setSide(order.isBid() ? OrderEvent.Side.BID : OrderEvent.Side.OFFER);
        event.setPrice(order.getPrice());
        event.setSize(order.getSize());
    }

    public static void main(String[] args) throws Exception {

        Executor executor = Executors.newCachedThreadPool();

        //must be power of 2
        int bufferSize = 1024;

        //TODO use non deprecated constructor
        Disruptor<OrderEvent> disruptor = new Disruptor<>(OrderEvent::new, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new OrderEventHandler());

        disruptor.start();

        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        for (int i = 0; true; i++) {
            Order order = new Order("ID-" + i, "XXX", "INS", true, i*100, i*10);
            ringBuffer.publishEvent(Xch::translate, order);
            Thread.sleep(1000);
        }
    }
}
