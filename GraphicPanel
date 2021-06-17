package main;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {

    JLabel subtitle, subtitle2;
    RectPanel rectPanel1, rectPanel2;
    JButton logView;

    public void addLabel(JPanel panel, String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setSize(300, 30);
        label.setLocation(x, y);
        panel.add(label);
    }

    public GraphicPanel() {
        setLayout(null);

        setSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        JLabel title = new JLabel("Panel View");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        add(title);

        subtitle = new JLabel("Current Time: " + Main.timeToString());
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setSize(600, 30);
        subtitle.setLocation(320, 60);
        add(subtitle);

        subtitle2 = new JLabel("Tickets Sold: " + Main.ticketingSystem.totalTickets);
        subtitle2.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle2.setSize(600, 30);
        subtitle2.setLocation(320, 80);
        add(subtitle2);

        // button
        logView = new JButton("Log View");
        logView.setFont(new Font("Arial", Font.PLAIN, 15));
        logView.setSize(200, 20);
        logView.setLocation(275, 450);
        logView.addActionListener((e) -> {
            Main.changePanel(Main.gamePanel);
        });
        add(logView);

        // rectangles
        rectPanel1 = new RectPanel(50, 120, 300, 300, Color.ORANGE, "Visit System");
        add(rectPanel1);
        rectPanel2 = new RectPanel(420, 120, 300, 300, Color.ORANGE, "Exit System");
        add(rectPanel2);

        // Frame configuration
        setSize(800, 600);
    }

    public synchronized void update() {
        subtitle.setText("Current Time: " + Main.timeToString());
        subtitle2.setText("Tickets Sold: " + Main.ticketingSystem.totalTickets);
        rectPanel1.removeAll();
        addLabel(rectPanel1, "Total Visitors: " + Main.visitSystem.getTotal(), 80, 180);
        int y = 200;
        for (Entrance entrance : Main.visitSystem.entrances) {
            addLabel(rectPanel1, String.format("Entrance %s: %d",
                    entrance.id,
                    entrance.getTotal()
            ), 80, y);
            y += 20;
            for (Turnstile turnstile : entrance.turnstiles) {
                addLabel(rectPanel1, String.format("Turnstile %s: %d",
                        turnstile.id,
                        turnstile.num
                ), 80, y);
                y += 20;
            }
        }
        rectPanel2.removeAll();
        addLabel(rectPanel2, "Total Visitors Who Have Exited: " + Main.exitSystem.getTotal(), 450, 180);
        y = 200;
        for (Exit exit : Main.exitSystem.exits) {
            addLabel(rectPanel2, String.format("Exit %s: %d",
                    exit.id,
                    exit.getTotal()
            ), 450, y);
            y += 20;
            for (Turnstile turnstile : exit.turnstiles) {
                addLabel(rectPanel2, String.format("Turnstile %s: %d",
                        turnstile.id,
                        turnstile.num
                ), 450, y);
                y += 20;
            }
        }
        revalidate();
        repaint();
    }
}
