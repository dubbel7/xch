package org.dubbel7.xch.matching.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class BookEntryTest {


    public static final long BOOK_PRICE = 110;
    BookEntry bookEntry;
    @Mock
    private MatchCallback callback;

    @Before
    public void before() {
        bookEntry = new BookEntry(BOOK_PRICE);
    }

    @Test
    public void addOrder() {

        bookEntry.addOrder("X1", 10);
        assertEquals(10, bookEntry.getSize());
        assertEquals(1, bookEntry.ordersSize());

        bookEntry.addOrder("X2", 11);
        assertEquals(21, bookEntry.getSize());
        assertEquals(2, bookEntry.ordersSize());
    }

    @Test
    public void matchFirstPartially() {

        bookEntry.addOrder("X1", 10);
        bookEntry.addOrder("X2", 11);

        long remaining = bookEntry.match("Z1", 6, callback);

        assertEquals(15, bookEntry.getSize());
        assertEquals(2, bookEntry.ordersSize());

        assertEquals(0, remaining);
        verify(callback).onMatch(eq("X1"), eq(6L), eq(BOOK_PRICE), eq(MatchType.PARTIAL_FILL));
        verify(callback).onMatch(eq("Z1"), eq(6L), eq(BOOK_PRICE), eq(MatchType.FILL));
        verifyNoMoreInteractions(callback);
    }

    @Test
    public void matchFirstCompletely() {

        bookEntry.addOrder("X1", 10);
        bookEntry.addOrder("X2", 11);

        long remaining = bookEntry.match("Z1", 10, callback);

        assertEquals(11, bookEntry.getSize());
        assertEquals(1, bookEntry.ordersSize());

        assertEquals(0, remaining);
        verify(callback).onMatch(eq("X1"), eq(10L), eq(BOOK_PRICE), eq(MatchType.FILL));
        verify(callback).onMatch(eq("Z1"), eq(10L), eq(BOOK_PRICE), eq(MatchType.FILL));
        verifyNoMoreInteractions(callback);
    }

    @Test
    public void matchFirstAndSecond() {

        bookEntry.addOrder("X1", 10);
        bookEntry.addOrder("X2", 11);

        long remaining = bookEntry.match("Z1", 12, callback);

        assertEquals(9, bookEntry.getSize());
        assertEquals(1, bookEntry.ordersSize());

        assertEquals(0, remaining);
        verify(callback).onMatch(eq("X1"), eq(10L), eq(BOOK_PRICE), eq(MatchType.FILL));
        verify(callback).onMatch(eq("Z1"), eq(10L), eq(BOOK_PRICE), eq(MatchType.PARTIAL_FILL));
        verify(callback).onMatch(eq("X2"), eq(2L), eq(BOOK_PRICE), eq(MatchType.PARTIAL_FILL));
        verify(callback).onMatch(eq("Z1"), eq(2L), eq(BOOK_PRICE), eq(MatchType.FILL));

        verifyNoMoreInteractions(callback);
    }

    @Test
    public void matchFullBook() {

        bookEntry.addOrder("X1", 10);
        bookEntry.addOrder("X2", 11);

        long remaining = bookEntry.match("Z1", 22, callback);

        assertEquals(0, bookEntry.getSize());
        assertEquals(0, bookEntry.ordersSize());

        assertEquals(1, remaining);
        verify(callback).onMatch(eq("X1"), eq(10L), eq(BOOK_PRICE), eq(MatchType.FILL));
        verify(callback).onMatch(eq("Z1"), eq(10L), eq(BOOK_PRICE), eq(MatchType.PARTIAL_FILL));
        verify(callback).onMatch(eq("X2"), eq(11L), eq(BOOK_PRICE), eq(MatchType.FILL));
        verify(callback).onMatch(eq("Z1"), eq(11L), eq(BOOK_PRICE), eq(MatchType.PARTIAL_FILL));

        verifyNoMoreInteractions(callback);
    }

}
