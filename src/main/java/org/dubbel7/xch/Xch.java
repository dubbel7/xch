package org.dubbel7.xch;

import org.dubbel7.xch.marketdata.TopOfBookDispatcher;
import org.dubbel7.xch.matching.Matcher;


public class Xch {



    public static void main(String[] args) throws Exception {

        TopOfBookDispatcher topOfBookDispatcher = new TopOfBookDispatcher();

        Matcher matcher = new Matcher(topOfBookDispatcher);

        for (int i = 0; true; i++) {
            Order order = new Order("ID-" + i, "XXX", "INS", true, i*100, i*10);
            matcher.onOrderReceived(order);
            Thread.sleep(1000);
        }
    }
}
