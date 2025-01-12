package calc.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import giava.gui.GRectangularButton;

public class Display extends JPanel {
    GRectangularButton type;
    JLabel expr;
    
    public Display(ActionListener list, Font numFont, Font textFont){
        type = new GRectangularButton("Standard");
        type.setFont(textFont);
        type.setForeground(Color.WHITE);
        type.addActionListener(list);
        type.setBackground(Color.GRAY.darker().darker());
        type.setFocusPainted(false);
        
        expr = new JLabel("",SwingConstants.RIGHT);
        expr.setFont(numFont);
        expr.setForeground(Color.WHITE);
        
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        add(type, BorderLayout.NORTH);
        add(expr, BorderLayout.CENTER);
    }
    
    public void changeExpr(String str){
        expr.setText(str);
    }

    public String getExpr(){
        return expr.getText();
    }
    
    public void changeTypeButton(String str){
        type.setText(str);
    }
}