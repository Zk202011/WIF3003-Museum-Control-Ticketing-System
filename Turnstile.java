package main;

public class Turnstile {
    public String id;
    public boolean isEntrance;

    public Turnstile(String id, boolean isEntrance) {
        this.id = id;
        this.isEntrance = isEntrance;
    }

    public void sensorTicket(Ticket ticket) {
        if (isEntrance) {
            Main.ticketsEntered.add(ticket);
            // print
            System.out.println(Main.timeToString() + ": Ticket " + ticket.id + " entered through Turnstile " + id +
                    ". Staying for " + ticket.duration + " minutes.");
        } else {
            Main.ticketsLeft.add(ticket);
            // print
            System.out.println(Main.timeToString() + ": Ticket " + ticket.id + " exited through Turnstile " + id + ".");
        }
    }
}
