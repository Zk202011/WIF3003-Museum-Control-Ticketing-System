package main;

public class Turnstile {
    public String id;
    public boolean isEntrance;
    public int num = 0;

    public Turnstile(String id, boolean isEntrance) {
        this.id = id;
        this.isEntrance = isEntrance;
    }

    public void log() {
        Main.gamePanel.log(String.format("Turnstile (%s): %d", id, num));
    }

    public void sensorTicket(Ticket ticket) {
        num++;
        if (isEntrance) {
            Main.ticketsEntered.add(ticket);
            // print
            Main.gamePanel.log(Main.timeToString() + ": Ticket " + ticket.id + " entered through Turnstile " + id +
                    ". Staying for " + ticket.duration + " minutes.");
        } else {
            Main.ticketsLeft.add(ticket);
            // print
            Main.gamePanel.log(Main.timeToString() + ": Ticket " + ticket.id + " exited through Turnstile " + id + ".");
        }
    }
}
