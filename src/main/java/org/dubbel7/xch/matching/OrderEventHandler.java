package org.dubbel7.xch.matching;

import com.lmax.disruptor.EventHandler;
import org.dubbel7.xch.marketdata.TopOfBookCallback;
import org.dubbel7.xch.matching.book.MatchType;
import org.dubbel7.xch.matching.book.OrderBook;

public class OrderEventHandler implements EventHandler<OrderEvent> {

    private final OrderBook book;
    private final TopOfBookCallback topOfBookCallback;

    public OrderEventHandler(OrderBook book, TopOfBookCallback topOfBookCallback) {
        this.book = book;
        this.topOfBookCallback = topOfBookCallback;
    }

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Received order event " + event);
        book.addOrder(event.getOrderId(),
                event.getSide() == OrderEvent.Side.BID,
                event.getSize(),
                event.getPrice(), this::matchCallback);

        //TODO check if top of book changed
        topOfBookCallback.onTopOfBook(book.getInstrumentId(),
                book.getTopBidPrice(),
                book.getTopBidSize(),
                book.getTopOfferPrice(),
                book.getTopOfferSize());
    }

    private void matchCallback(String orderId, long size, long price, MatchType type) {
        System.out.println("Match: " + orderId + ", size: " + size + ", price: " + price + ", type: " + type);
    }
}
