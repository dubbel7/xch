package org.dubbel7.xch.matching.book;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class SideBook {

    private final static BookEntryComparator BID_BOOK_COMPARATOR = new BookEntryComparator(true);
    private final static BookEntryComparator ASK_BOOK_COMPARATOR = new BookEntryComparator(false);

    private final boolean bidBook;
    private final BookEntryComparator comparator;

    private final List<BookEntry> entries = new ArrayList<>();

    public SideBook(boolean bidBook) {
        this.bidBook = bidBook;
        comparator = bidBook ? BID_BOOK_COMPARATOR : ASK_BOOK_COMPARATOR;
    }


    public long sizeAt(int index) {
        if (entries.size() > index) {
            return entries.get(index).getSize();
        } else {
            return 0;
        }
    }

    public long priceAt(int index) {
        if (entries.size() > index) {
            return entries.get(index).getPrice();
        } else {
            return 0;
        }
    }

    public void addOrder(String orderId, long size, long price) {
        boolean added = false;
        for (BookEntry be : entries) {
            if (be.getPrice() == price) {
                be.addOrder(orderId, size);
                added = true;
            }
        }
        if (!added) {
            BookEntry be = new BookEntry(price);
            be.addOrder(orderId, size);
            entries.add(be);
            entries.sort(comparator);
        }
    }

    public long match(final String orderId,
                             final long size,
                             final long price,
                             final MatchCallback matchCallback) {
        long remainingSize = size;

        ListIterator<BookEntry> bookEntryListIterator = entries.listIterator();
        while (bookEntryListIterator.hasNext()) {
            BookEntry be = bookEntryListIterator.next();
            boolean match = bidBook ? price <= be.getPrice() : price >= be.getPrice();
            if (match) {
                remainingSize = be.match(orderId, remainingSize, matchCallback);
                if (be.getSize() == 0) {
                    bookEntryListIterator.remove();
                }
            } else {
                break;
            }
        }
        return remainingSize;
    }

    public int getSize() {
        return entries.size();
    }

    public static class BookEntryComparator implements Comparator<BookEntry> {
        private final boolean bid;

        public BookEntryComparator(boolean bid) {
            this.bid = bid;
        }

        @Override
        public int compare(BookEntry o1, BookEntry o2) {
            if (bid) {
                return Long.compare(o2.getPrice(), o1.getPrice());
            } else {
                return Long.compare(o1.getPrice(), o2.getPrice());
            }
        }
    }
}
