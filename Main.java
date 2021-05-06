package main;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final int START_TICKET_TIME = 480;
    public static final int START_TIME = 540;
    public static final int END_TICKET_TIME = 1020;
    public static final int END_TIME = 1080;

    static AtomicInteger time = new AtomicInteger(START_TICKET_TIME);
    static TicketingSystem ticketingSystem;
    static VisitSystem visitSystem;
    static ExitSystem exitSystem;
    static LinkedList<Ticket> tickets = new LinkedList<>();
    static LinkedList<Ticket> ticketsEntered = new LinkedList<>();
    static LinkedList<Ticket> ticketsLeft = new LinkedList<>();

    public static String timeToString() {
        String h = Integer.toString(time.get() / 60);
        if (h.length() == 1) h = "0" + h;
        String m = Integer.toString(time.get() % 60);
        if (m.length() == 1) m = "0" + m;
        return h + m;
    }

    public static void main(String[] args) {
        // new ticketing system
        ticketingSystem = new TicketingSystem();
        visitSystem = new VisitSystem();
        exitSystem = new ExitSystem();
        while (time.get() <= END_TIME) {
            // buy tickets
            ticketingSystem.buy();
            // visit museum
            boolean canVisit = true;
            while (canVisit) canVisit = visitSystem.visit();
            // exit museum
            exitSystem.exit();
            // move time
            time.incrementAndGet();
        }
    }
}
