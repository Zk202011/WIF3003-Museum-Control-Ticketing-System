package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    JTextArea logs;
    JButton graphic;

    public GamePanel() {
        setLayout(null);

        JLabel title = new JLabel("Log View");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(325, 30);
        add(title);

        JLabel subtitle = new JLabel("Check the logs for the activities.");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setSize(600, 30);
        subtitle.setLocation(280, 60);
        add(subtitle);

        // inputs
        logs = new JTextArea();
        logs.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scroll = new JScrollPane(logs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(50, 100, 700, 300);
        add(scroll);

        // button
        graphic = new JButton("Panel View");
        graphic.setFont(new Font("Arial", Font.PLAIN, 15));
        graphic.setSize(200, 20);
        graphic.setLocation(275, 450);
        graphic.addActionListener((e) -> {
            Main.changePanel(Main.graphicPanel);
        });
        add(graphic);
    }

    public synchronized void log(String msg) {
        String text = logs.getText();
        text = msg + "\n" + text;
        logs.setText(text);
    }
}
