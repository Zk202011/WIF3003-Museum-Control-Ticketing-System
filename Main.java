package main;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static volatile int START_TICKET_TIME = 480;
    public static volatile int START_TIME = 540;
    public static volatile int END_TICKET_TIME = 1020;
    public static volatile int END_TIME = 1080;
    public static volatile int MAX_HOURLY_LIMIT = 100;
    public static volatile int MAX_DAILY_LIMIT = 900;

    static volatile int time = START_TICKET_TIME;
    static TicketingSystem ticketingSystem;
    static VisitSystem visitSystem;
    static ExitSystem exitSystem;
    static LinkedList<Ticket> tickets = new LinkedList<>();
    static PriorityBlockingQueue<Ticket> ticketsEntered = new PriorityBlockingQueue<>();
    static LinkedList<Ticket> ticketsLeft = new LinkedList<>();
    public static InputPanel inputPanel;
    public static GamePanel gamePanel;
    public static GraphicPanel graphicPanel;
    public static JFrame mainFrame;

    public static String timeToString() {
        String h = Integer.toString(time / 60);
        if (h.length() == 1) h = "0" + h;
        String m = Integer.toString(time % 60);
        if (m.length() == 1) m = "0" + m;
        return h + ":" + m;
    }

    public static void changePanel(JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        // new ticketing system
        ticketingSystem = new TicketingSystem();
        visitSystem = new VisitSystem();
        exitSystem = new ExitSystem();

        // initialize panels
        inputPanel = new InputPanel();
        gamePanel = new GamePanel();
        graphicPanel = new GraphicPanel();

        // configure frame
        mainFrame = new JFrame("Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.add(inputPanel);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}
