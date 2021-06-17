package main;

import java.util.Random;

public class TicketingSystem {
    int nextPurchaseTime = 0;
    int totalTickets = 0;

    public void buy() {
        // buy 1-4 tickets
        int t = new Random().nextInt(4) + 1;
        int t2 = new Random().nextInt(4) + 1;
        // skip if not yet next purchase
        if (nextPurchaseTime != 0 && nextPurchaseTime != Main.time) {
            return;
        }
        // skip if > max daily limit
        if (totalTickets + t > Main.MAX_DAILY_LIMIT) {
            Main.gamePanel.log(Main.timeToString() + ": Unable to purchase: Exceed daily limit.");
            return;
        }
        // set next purchase
        nextPurchaseTime = Main.time + t2;
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
            Main.tickets.add(new Ticket(id, Main.time));
            // print
            Main.gamePanel.log(Main.timeToString() + ": Sold ticket " + id);
        }
        totalTickets += t;
        return;
    }
}
