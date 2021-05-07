package main;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static volatile int START_TICKET_TIME = 480;
    public static volatile int START_TIME = 540;
    public static volatile int END_TICKET_TIME = 1020;
    public static volatile int END_TIME = 1080;

    static volatile int time = START_TICKET_TIME;
    static TicketingSystem ticketingSystem;
    static VisitSystem visitSystem;
    static ExitSystem exitSystem;
    static LinkedList<Ticket> tickets = new LinkedList<>();
    static PriorityBlockingQueue<Ticket> ticketsEntered = new PriorityBlockingQueue<>();
    static LinkedList<Ticket> ticketsLeft = new LinkedList<>();

    public static boolean input() {
        Scanner in = new Scanner(System.in);
        String text = "";
        System.out.print("Start ticket time (default 8:00): ");
        text = in.nextLine();
        if (!text.equals("")) {
            try {
                text=Integer.parseInt(text)*60+"";
                START_TICKET_TIME = Integer.parseInt(text);
                time = START_TICKET_TIME;
            } catch (Exception ignored) {
                return false;
            }
        } else {
            START_TICKET_TIME = 480;
            time = 480;
        }
        System.out.print("Start time (default 9:00): ");
        text = in.nextLine();
        if (!text.equals("")) {
            try {
                text=Integer.parseInt(text)*60+"";
                START_TIME = Integer.parseInt(text);
            } catch (Exception ignored) {
                return false;
            }
        } else {
            START_TIME = 540;
        }
        System.out.print("End ticket time (default 17:00): ");
        text = in.nextLine();
        if (!text.equals("")) {
            try {
                text=Integer.parseInt(text)*60+"";
                END_TICKET_TIME = Integer.parseInt(text);
            } catch (Exception ignored) {
                return false;
            }
        } else {
            END_TICKET_TIME = 1020;
        }
        System.out.print("End time (default 18:00): ");
        text = in.nextLine();
        if (!text.equals("")) {
            try {
                text=Integer.parseInt(text)*60+"";
                END_TIME = Integer.parseInt(text);
            } catch (Exception ignored) {
                return false;
            }
        } else {
            END_TIME = 1080;
        }
        // validate
        if(START_TICKET_TIME > START_TIME) {
            System.out.println("Start ticket time cannot be later than start time.");
            return false;
        }
        if(START_TIME > END_TIME) {
            System.out.println("Start time cannot be later than end time.");
            return false;
        }
        if(END_TICKET_TIME > END_TIME) {
            System.out.println("End ticket time cannot be later than end time.");
            return false;
        }
        return true;
    }

    public static String timeToString() {
        String h = Integer.toString(time / 60);
        if (h.length() == 1) h = "0" + h;
        String m = Integer.toString(time % 60);
        if (m.length() == 1) m = "0" + m;
        return h + m;
    }

    public static void main(String[] args) throws InterruptedException {
        boolean valid = input();
        while(!valid) valid = input();
        // new ticketing system
        ticketingSystem = new TicketingSystem();
        visitSystem = new VisitSystem();
        exitSystem = new ExitSystem();

        while (time <= END_TIME) {
            // threads
            Thread visitThread = new Thread(visitSystem);
            Thread exitThread = new Thread(exitSystem);
            // buy tickets
            ticketingSystem.buy();
            visitThread.start();
            exitThread.start();
            // move time
            time++;
            // sleep
            Thread.sleep(100);
        }

        // report
        System.out.println("Total tickets sold: " + ticketingSystem.totalTickets);
        System.out.println("Total tickets visited: " + visitSystem.totalTickets);
        System.out.println("Total tickets exit: " + ticketsLeft.size());
    }
}

