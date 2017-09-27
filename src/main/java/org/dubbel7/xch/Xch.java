package org.dubbel7.xch;

import org.dubbel7.xch.matching.Matcher;
import org.dubbel7.xch.matching.OrderEvent;


public class Xch {



    public static void main(String[] args) throws Exception {

        Matcher matcher = new Matcher();

        for (int i = 0; true; i++) {
            Order order = new Order("ID-" + i, "XXX", "INS", true, i*100, i*10);
            matcher.onOrderReceived(order);
            Thread.sleep(1000);
        }
    }
}
