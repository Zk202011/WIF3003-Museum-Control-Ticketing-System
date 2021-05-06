package main;

import java.util.ArrayList;
import java.util.Random;

public class Entrance {
    public String id;
    public ArrayList<Turnstile> turnstiles = new ArrayList<>();

    public Entrance(String id) {
        this.id = id;
        for (int i = 1; i <= 4; i++) {
            turnstiles.add(new Turnstile(id + "T" + i, true));
        }
    }

    public void visit(Ticket ticket) {
        int i = new Random().nextInt(4);
        turnstiles.get(i).sensorTicket(ticket);
    }
}
