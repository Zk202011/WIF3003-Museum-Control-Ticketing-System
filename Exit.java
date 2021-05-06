package main;

import java.util.ArrayList;
import java.util.Random;

public class Exit {
    public String id;
    public ArrayList<Turnstile> turnstiles = new ArrayList<>();

    public Exit(String id) {
        this.id = id;
        for (int i = 1; i <= 4; i++) {
            turnstiles.add(new Turnstile(id + "T" + i, false));
        }
    }

    public void exit(Ticket ticket) {
        int i = new Random().nextInt(4);
        turnstiles.get(i).sensorTicket(ticket);
    }
}
