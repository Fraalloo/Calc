package giava.gui;

import java.awt.Graphics;
import java.awt.Dimension;

public class GCircularButton extends GButton {
    public GCircularButton(String str){
        super(str);
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(getModel().isPressed() ? getBackground().darker() : getBackground());
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g){
        g.setColor(getModel().isPressed() ? getBackground().darker() : getBackground());
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public boolean contains(int x, int y){
        int radius = getWidth() / 2;
        return (x - radius) * (x - radius) + (y - radius) * (y - radius) <= radius * radius;
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(50, 50);
    }
}