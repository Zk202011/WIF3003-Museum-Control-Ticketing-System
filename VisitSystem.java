package main;

import java.util.ArrayList;
import java.util.Random;

public class VisitSystem implements Runnable {
    public ArrayList<Entrance> entrances = new ArrayList<>();
    int totalTickets = 0;

    public VisitSystem() {
        entrances.add(new Entrance("SE"));
        entrances.add(new Entrance("NE"));
    }

    public boolean visit() {
        // check if can allow more visitors
        if (Main.ticketsEntered.size() >= Main.MAX_HOURLY_LIMIT ||
                Main.tickets.size() == 0 ||
                Main.time < Main.START_TIME ||
                Main.time > Main.END_TIME) {
            return false;
        }
        // get ticket
        Ticket ticket = Main.tickets.pollFirst();
        if (ticket == null) return false;
        // set ticket duration and exit time
        ticket.duration = new Random().nextInt(101) + 50;
        ticket.exitTime = Main.time + ticket.duration;
        // visit
        int i = new Random().nextInt(2);
        entrances.get(i).visit(ticket);
        totalTickets++;
        return true;
    }

    @Override
    public void run() {
        while (true) {
            boolean canVisit = visit();
            if (!canVisit) break;
        }
    }
}
