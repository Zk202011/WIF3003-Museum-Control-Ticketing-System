package main;

import java.util.Random;

public class TicketingSystem {
    int nextPurchaseTime = 0;
    int totalTickets = 0;

    public int buy() {
        // buy 1-4 tickets
        int t = new Random().nextInt(4) + 1;
        int t2 = new Random().nextInt(4) + 1;
        // skip if not yet next purchase
        if (nextPurchaseTime != 0 && nextPurchaseTime != Main.time.get()) {
            return 0;
        }
        // skip if > 900 daily
        if (totalTickets + t > 900) {
            System.out.println(Main.timeToString() + ": Unable to purchase: Exceed daily limit.");
            return 0;
        }
        // set next purchase
        nextPurchaseTime = Main.time.get() + t2;
        if (nextPurchaseTime > Main.END_TICKET_TIME) nextPurchaseTime = -1;
        // add tickets
        for (int i = 0; i < t; i++) {
            // generate ID
            String id = Integer.toString(totalTickets + i + 1);
            if (id.length() == 1) id = "T000" + id;
            else if (id.length() == 2) id = "T00" + id;
            else if (id.length() == 3) id = "T0" + id;
            else id = "T" + id;
            // add ticket
            Main.tickets.add(new Ticket(id, Main.time.get()));
            // print
            System.out.println(Main.timeToString() + ": Sold ticket " + id);
        }
        totalTickets += t;
        return t;
    }

}
