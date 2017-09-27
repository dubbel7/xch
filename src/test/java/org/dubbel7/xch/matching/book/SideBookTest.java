package org.dubbel7.xch.matching.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SideBookTest {

    SideBook bidBook;
    SideBook offerBook;

    @Mock
    private MatchCallback callback;

    @Before
    public void before() {
        bidBook = new SideBook(true);
        offerBook = new SideBook(false);
    }

    @Test
    public void addingOrders_bidBook() {

        bidBook.addOrder("XXX", 10, 111);
        assertEquals(1, bidBook.getSize());
        assertEquals(111, bidBook.priceAt(0));
        assertEquals(10, bidBook.sizeAt(0));

        bidBook.addOrder("XXX", 12, 112);
        assertEquals(2, bidBook.getSize());
        assertEquals("this is BID book, so the best price is the highest, hence top of the book", 112, bidBook.priceAt(0));
        assertEquals(12, bidBook.sizeAt(0));
        assertEquals(111, bidBook.priceAt(1));
        assertEquals(10, bidBook.sizeAt(1));

        bidBook.addOrder("XXX", 5, 112);
        assertEquals(2, bidBook.getSize());
        assertEquals(112, bidBook.priceAt(0));
        assertEquals(17, bidBook.sizeAt(0));
        assertEquals(111, bidBook.priceAt(1));
        assertEquals(10, bidBook.sizeAt(1));

    }

    @Test
    public void addingOrders_offerBook() {

        offerBook.addOrder("XXX", 10, 111);
        assertEquals(1, offerBook.getSize());
        assertEquals(111, offerBook.priceAt(0));
        assertEquals(10, offerBook.sizeAt(0));

        offerBook.addOrder("XXX", 12, 112);
        assertEquals(2, offerBook.getSize());
        assertEquals("this is OFFER book, so the best price is the lowest, hence top of the book", 111, offerBook.priceAt(0));
        assertEquals(10, offerBook.sizeAt(0));
        assertEquals(112, offerBook.priceAt(1));
        assertEquals(12, offerBook.sizeAt(1));

        offerBook.addOrder("XXX", 2, 112);
        assertEquals(2, offerBook.getSize());
        assertEquals("this is OFFER book, so the best price is the lowest, hence top of the book", 111, offerBook.priceAt(0));
        assertEquals(10, offerBook.sizeAt(0));
        assertEquals(112, offerBook.priceAt(1));
        assertEquals(14, offerBook.sizeAt(1));
    }

    @Test
    public void match_bidBookOneLevelPartialOneOrder() {
        bidBook.addOrder("X1", 10, 111);
        bidBook.addOrder("X2", 12, 112);
        bidBook.addOrder("X3", 5, 112);

        assertEquals(2, bidBook.getSize());
        assertEquals(112, bidBook.priceAt(0));
        assertEquals(17, bidBook.sizeAt(0));
        assertEquals(111, bidBook.priceAt(1));
        assertEquals(10, bidBook.sizeAt(1));

        // offer order 1@112
        long remaingSize = bidBook.match("Z1", 1, 112, callback);
        assertEquals(0, remaingSize);
        verify(callback).onMatch("X2", 1, 112, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("Z1", 1, 112, MatchType.FILL);
    }

    @Test
    public void match_bidBookOneLevelPartialTwoOrders() {
        bidBook.addOrder("X1", 10, 111);
        bidBook.addOrder("X2", 12, 112);
        bidBook.addOrder("X3", 5, 112);

        assertEquals(2, bidBook.getSize());
        assertEquals(112, bidBook.priceAt(0));
        assertEquals(17, bidBook.sizeAt(0));
        assertEquals(111, bidBook.priceAt(1));
        assertEquals(10, bidBook.sizeAt(1));

        // offer order 14@112
        long remaingSize = bidBook.match("Z1", 14, 112, callback);
        assertEquals(0, remaingSize);
        verify(callback).onMatch("X2", 12, 112, MatchType.FILL);
        verify(callback).onMatch("Z1", 12, 112, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("X3", 2, 112, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("Z1", 2, 112, MatchType.FILL);
    }

    @Test
    public void match_bidBookOneLevelPartialTwoLevels() {
        bidBook.addOrder("X1", 10, 111);
        bidBook.addOrder("X2", 12, 112);
        bidBook.addOrder("X3", 5, 112);

        assertEquals(2, bidBook.getSize());
        assertEquals(112, bidBook.priceAt(0));
        assertEquals(17, bidBook.sizeAt(0));
        assertEquals(111, bidBook.priceAt(1));
        assertEquals(10, bidBook.sizeAt(1));

        // offer order 30@111
        long remaingSize = bidBook.match("Z1", 30, 111, callback);
        verify(callback).onMatch("X2", 12, 112, MatchType.FILL);
        verify(callback).onMatch("Z1", 12, 112, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("X3", 5, 112, MatchType.FILL);
        verify(callback).onMatch("Z1", 5, 112, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("X1", 10, 111, MatchType.FILL);
        verify(callback).onMatch("Z1", 10, 111, MatchType.PARTIAL_FILL);
        assertEquals(3, remaingSize);
    }


    @Test
    public void match_offerBookOneLevelPartialOneOrder() {
        offerBook.addOrder("X1", 10, 111);
        offerBook.addOrder("X2", 12, 111);
        offerBook.addOrder("X3", 2, 112);

        // bid order 1@111
        long remaingSize = offerBook.match("Z1", 1, 111, callback);
        assertEquals(0, remaingSize);
        verify(callback).onMatch("X1", 1, 111, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("Z1", 1, 111, MatchType.FILL);
    }

    @Test
    public void match_offerBookOneLevelPartialTwoOrders() {
        offerBook.addOrder("X1", 10, 111);
        offerBook.addOrder("X2", 12, 111);
        offerBook.addOrder("X3", 2, 112);

        // offer order 14@112
        long remaingSize = offerBook.match("Z1", 14, 112, callback);
        verify(callback).onMatch("X1", 10, 111, MatchType.FILL);
        verify(callback).onMatch("Z1", 10, 111, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("X2", 4, 111, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("Z1", 4, 111, MatchType.FILL);
        assertEquals(0, remaingSize);
    }

    @Test
    public void match_offerBookOneLevelPartialTwoLevels() {
        offerBook.addOrder("X1", 10, 111);
        offerBook.addOrder("X2", 12, 111);
        offerBook.addOrder("X3", 2, 112);

        // offer order 30@112
        long remaingSize = offerBook.match("Z1", 30, 112, callback);
        verify(callback).onMatch("X1", 10, 111, MatchType.FILL);
        verify(callback).onMatch("Z1", 10, 111, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("X2", 12, 111, MatchType.FILL);
        verify(callback).onMatch("Z1", 12, 111, MatchType.PARTIAL_FILL);
        verify(callback).onMatch("X3", 2, 112, MatchType.FILL);
        verify(callback).onMatch("Z1", 2, 112, MatchType.PARTIAL_FILL);
        assertEquals(6, remaingSize);
    }
}
