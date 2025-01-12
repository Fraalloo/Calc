package giava.gui;

import java.awt.Graphics;

public class GRectangularButton extends GButton {
    public GRectangularButton(String str){
        super(str);
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(getModel().isPressed() ? getBackground().darker() : getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g){
        g.setColor(getModel().isPressed() ? getBackground().darker() : getBackground());
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
