package main;

import javax.swing.*;
import java.awt.*;

class RectPanel extends JPanel {

    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private final Color color;
    String text;

    public RectPanel(int x, int y, int w, int h, Color color, String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.text = text;
        setLayout(null);
        setSize(new Dimension(w + 2 * x, h + 2 * y));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w + 2 * x, h + 2 * y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(x, y, w, h);
        g.setColor(color);
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(text, x + 100, y + 50);
    }
}
