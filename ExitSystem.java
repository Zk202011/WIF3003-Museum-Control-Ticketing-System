package main;

import java.util.ArrayList;
import java.util.Random;

public class ExitSystem implements Runnable {
    public ArrayList<Exit> exits = new ArrayList<>();

    public ExitSystem() {
        exits.add(new Exit("EE"));
        exits.add(new Exit("WE"));
    }

    public void exit() {
        while (true) {
            Ticket currentTicket = Main.ticketsEntered.peek();
            // skip checking if no more ticket
            if (currentTicket == null) {
                break;
            }
            // skip checking if does not exceed time
            if (Main.time < currentTicket.exitTime && Main.time != Main.END_TIME) {
                break;
            }
            // exit if reached exit time or the museum is closing
            // remove from tickets entered
            Main.ticketsEntered.poll();
            // exit
            exits.get(new Random().nextInt(2)).exit(currentTicket);
        }
    }

    public void log() {
        Main.gamePanel.log("Exit system: " + getTotal());
        for (Exit exit : exits) exit.log();
    }

    public int getTotal() {
        int tmp = 0;
        for (Exit t : exits) tmp += t.getTotal();
        return tmp;
    }

    @Override
    public void run() {
        exit();
    }
}
