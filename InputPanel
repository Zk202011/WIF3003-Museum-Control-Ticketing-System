package main;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

public class InputPanel extends JPanel {
    JTextField startTicketTime;
    JTextField startTime;
    JTextField endTicketTime;
    JTextField endTime;
    JTextField maxDailyLimit;
    JTextField maxHourlyLimit;

    public InputPanel() {
        setLayout(null);

        JLabel title = new JLabel("Enter Inputs");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        add(title);

        JLabel subtitle = new JLabel("Fill in the fields below (In Minutes, 0 = 12 am)");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setSize(600, 30);
        subtitle.setLocation(250, 60);
        add(subtitle);

        // inputs
        JLabel startTicketTimeLabel = new JLabel("Start Ticket Time");
        startTicketTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        startTicketTimeLabel.setSize(300, 20);
        startTicketTimeLabel.setLocation(100, 100);
        add(startTicketTimeLabel);

        startTicketTime = new JTextField();
        startTicketTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTicketTime.setSize(190, 20);
        startTicketTime.setLocation(300, 100);
        startTicketTime.setText("480");
        add(startTicketTime);

        JLabel startTimeLabel = new JLabel("Start Time");
        startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        startTimeLabel.setSize(300, 20);
        startTimeLabel.setLocation(100, 150);
        add(startTimeLabel);

        startTime = new JTextField();
        startTime.setFont(new Font("Arial", Font.PLAIN, 15));
        startTime.setSize(190, 20);
        startTime.setLocation(300, 150);
        startTime.setText("540");
        add(startTime);

        JLabel endTicketTimeLabel = new JLabel("End Ticket Time");
        endTicketTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endTicketTimeLabel.setSize(300, 20);
        endTicketTimeLabel.setLocation(100, 200);
        add(endTicketTimeLabel);

        endTicketTime = new JTextField();
        endTicketTime.setFont(new Font("Arial", Font.PLAIN, 15));
        endTicketTime.setSize(190, 20);
        endTicketTime.setLocation(300, 200);
        endTicketTime.setText("1020");
        add(endTicketTime);

        JLabel endTimeLabel = new JLabel("End Time");
        endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endTimeLabel.setSize(300, 20);
        endTimeLabel.setLocation(100, 250);
        add(endTimeLabel);

        endTime = new JTextField();
        endTime.setFont(new Font("Arial", Font.PLAIN, 15));
        endTime.setSize(190, 20);
        endTime.setLocation(300, 250);
        endTime.setText("1080");
        add(endTime);

        JLabel maxDailyLimitLabel = new JLabel("Max Daily Limit");
        maxDailyLimitLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        maxDailyLimitLabel.setSize(300, 20);
        maxDailyLimitLabel.setLocation(100, 300);
        add(maxDailyLimitLabel);

        maxDailyLimit = new JTextField();
        maxDailyLimit.setFont(new Font("Arial", Font.PLAIN, 15));
        maxDailyLimit.setSize(190, 20);
        maxDailyLimit.setLocation(300, 300);
        maxDailyLimit.setText("900");
        add(maxDailyLimit);

        JLabel maxHourlyLimitLabel = new JLabel("Max Hourly Limit");
        maxHourlyLimitLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        maxHourlyLimitLabel.setSize(300, 20);
        maxHourlyLimitLabel.setLocation(100, 350);
        add(maxHourlyLimitLabel);

        maxHourlyLimit = new JTextField();
        maxHourlyLimit.setFont(new Font("Arial", Font.PLAIN, 15));
        maxHourlyLimit.setSize(190, 20);
        maxHourlyLimit.setLocation(300, 350);
        maxHourlyLimit.setText("100");
        add(maxHourlyLimit);

        // button
        JButton submit = new JButton("Confirm");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(270, 450);
        submit.addActionListener((e) -> {
            validateAndStart();
        });
        add(submit);
    }

    public void validateAndStart() {
        try {
            Main.START_TICKET_TIME = Integer.parseInt(startTicketTime.getText());
            Main.time = Main.START_TICKET_TIME;
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Invalid start ticket time.");
            return;
        }
        try {
            Main.START_TIME = Integer.parseInt(startTime.getText());
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Invalid start time.");
            return;
        }
        try {
            Main.END_TICKET_TIME = Integer.parseInt(endTicketTime.getText());
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Invalid end ticket time.");
            return;
        }
        try {
            Main.END_TIME = Integer.parseInt(endTime.getText());
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Invalid end time.");
            return;
        }
        // validate
        if (Main.START_TICKET_TIME > Main.START_TIME) {
            JOptionPane.showMessageDialog(this, "Start ticket time cannot be later than start time.");
            return;
        }
        if (Main.START_TIME > Main.END_TIME) {
            JOptionPane.showMessageDialog(this, "Start time cannot be later than end time.");
            return;
        }
        if (Main.END_TICKET_TIME > Main.END_TIME) {
            JOptionPane.showMessageDialog(this, "End ticket time cannot be later than end time.");
            return;
        }
        try {
            Main.MAX_DAILY_LIMIT = Integer.parseInt(maxDailyLimit.getText());
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Invalid max daily limit.");
            return;
        }
        try {
            Main.MAX_HOURLY_LIMIT = Integer.parseInt(maxHourlyLimit.getText());
        } catch (Exception ignored) {
            JOptionPane.showMessageDialog(this, "Invalid max hourly limit.");
            return;
        }
        setVisible(false);
        Main.gamePanel.logs.setText("");
        // new ticketing system
        Main.ticketingSystem = new TicketingSystem();
        Main.visitSystem = new VisitSystem();
        Main.exitSystem = new ExitSystem();
        Main.tickets = new LinkedList<>();
        Main.ticketsEntered = new PriorityBlockingQueue<>();
        Main.ticketsLeft = new LinkedList<>();
        Main.changePanel(Main.gamePanel);

        // start game with new thread
        new Thread(() -> {
            while (Main.time <= Main.END_TIME) {
                // threads
                Thread visitThread = new Thread(Main.visitSystem);
                Thread exitThread = new Thread(Main.exitSystem);
                // buy tickets
                Main.ticketingSystem.buy();
                visitThread.start();
                exitThread.start();
                // sleep
                try {
                    visitThread.join();
                    exitThread.join();
                    Thread.sleep(100);
                } catch (Exception ignored) {

                }
                // update graphic frame
                Main.graphicPanel.update();
                // move time
                Main.time++;
            }
            // report
            Main.gamePanel.log("Total tickets sold: " + Main.ticketingSystem.totalTickets);
            Main.gamePanel.log("Total tickets visited: " + Main.visitSystem.totalTickets);
            Main.gamePanel.log("Total tickets exit: " + Main.ticketsLeft.size());
            Main.gamePanel.log("");
            Main.visitSystem.log();
            Main.gamePanel.log("");
            Main.exitSystem.log();
            // debug
            for (Ticket t : Main.ticketsEntered) {
                Main.gamePanel.log(t.id + " has not left yet.");
            }
        }).start();

    }
}
