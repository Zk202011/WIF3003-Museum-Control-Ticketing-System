package main;

import java.util.ArrayList;
import java.util.Random;

public class ExitSystem {
    public ArrayList<Exit> exits = new ArrayList<>();

    public ExitSystem() {
        exits.add(new Exit("EE"));
        exits.add(new Exit("WE"));
    }

    public boolean exit() {
        // for each visitor
        for (int i = 0; i < Main.ticketsEntered.size(); i++) {
            Ticket currentTicket = Main.ticketsEntered.get(i);
            // exit if reached exit time or the museum is closing
            if (Main.time.get() >= currentTicket.exitTime || Main.time.get() == Main.END_TIME) {
                // remove from tickets entered
                Main.ticketsEntered.remove(i);
                // reduce index by 1
                i--;
                // add to tickets left
                Main.ticketsLeft.add(currentTicket);
                // exit
                exits.get(new Random().nextInt(2)).exit(currentTicket);
            }
        }

        return true;
    }
}
