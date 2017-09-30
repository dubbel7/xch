package org.dubbel7.xch.marketdata;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TopOfBookDispatcher implements TopOfBookCallback{

    private final Disruptor<TopOfBookEvent> disruptor;

    public TopOfBookDispatcher() {
        Executor executor = Executors.newCachedThreadPool();
        //must be power of 2
        int bufferSize = 1024;
        //TODO use non deprecated constructor
        disruptor = new Disruptor<>(TopOfBookEvent::new, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new TopOfBookEventHandler());
        disruptor.start();

    }

    @Override
    public void onTopOfBook(String instrumentId, long bidPrice, long bidSize, long offerPrice, long offerSize) {

        RingBuffer<TopOfBookEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            TopOfBookEvent event = ringBuffer.get(sequence);
            event.setInstrumentId(instrumentId);
            event.setBidPrice(bidPrice);
            event.setBidSize(bidSize);
            event.setOfferPrice(offerPrice);
            event.setOfferSize(offerSize);
        }
        finally {
            ringBuffer.publish(sequence);
        }
    }
}
