package org.dubbel7.xch.book;

import org.junit.Before;
import org.junit.Test;

public class SideBookTest {

    SideBook bidBook;
    SideBook offerBook;

    @Before
    public void before() {
        bidBook = new SideBook(true);
        offerBook = new SideBook(false);
    }

    @Test
    public void addBidOrder() {

        bidBook.addOrder("XXX", true, 10, 111);

    }
}
