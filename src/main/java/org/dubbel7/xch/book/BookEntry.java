package org.dubbel7.xch.book;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BookEntry {

    private final long price;
    private long size;
    private List<OrderEntry> orders = new ArrayList<>(); //TODO initial size

    public BookEntry(final long price) {
        this.price = price;
    }

    public void addOrder(final String orderId, final long orderSize) {
        size += orderSize;
        orders.add(new OrderEntry(orderId, orderSize));
    }

    public long match(final String orderId, final long orderSize, final MatchCallback matchCallback) {
        long remainingSize = orderSize;

        ListIterator<OrderEntry> orderEntryListIterator = orders.listIterator();
        while (orderEntryListIterator.hasNext()) {
            OrderEntry order = orderEntryListIterator.next();
            long matchSize;
            if (order.remainingSize <= remainingSize) {
                matchSize = order.remainingSize;
                remainingSize -= order.remainingSize;
                order.remainingSize = 0;
                orderEntryListIterator.remove();
            } else {
                matchSize = remainingSize;
                order.remainingSize -= remainingSize;
                remainingSize = 0;
            }
            matchCallback.onMatch(order.orderId, matchSize, price, order.remainingSize == 0 ? MatchType.FILL : MatchType.PARTIAL_FILL);
            matchCallback.onMatch(orderId, matchSize, price, remainingSize == 0 ? MatchType.FILL : MatchType.PARTIAL_FILL);
            this.size -= matchSize;
            if (remainingSize == 0) {
                break;
            }
        }
        return remainingSize;
    }

    public long getSize() {
        return size;
    }

    public int ordersSize() {
        return orders.size();
    }

    private static class OrderEntry {
        String orderId;
        long remainingSize;

        private OrderEntry(String orderId, long remainingSize) {
            this.orderId = orderId;
            this.remainingSize = remainingSize;
        }
    }
}
