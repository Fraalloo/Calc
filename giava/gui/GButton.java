package giava.gui;

import javax.swing.JButton;

public class GButton extends JButton {
    public GButton(String str){
        super(str);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
}
