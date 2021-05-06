package main;

import java.util.ArrayList;
import java.util.Random;

public class VisitSystem {
    public ArrayList<Entrance> entrances = new ArrayList<>();

    public VisitSystem() {
        entrances.add(new Entrance("SE"));
        entrances.add(new Entrance("NE"));
    }

    public boolean visit() {
        // check if can allow more visitors
        if (Main.ticketsEntered.size() >= 100 ||
                Main.tickets.size() == 0 ||
                Main.time.get() < Main.START_TIME ||
                Main.time.get() > Main.END_TIME) {
            return false;
        }
        // get ticket
        Ticket ticket = Main.tickets.pollFirst();
        if (ticket == null) return false;
        // set ticket duration and exit time
        ticket.duration = new Random().nextInt(101) + 50;
        ticket.exitTime = Main.time.get() + ticket.duration;
        // visit
        int i = new Random().nextInt(2);
        entrances.get(i).visit(ticket);
        return true;
    }
}
