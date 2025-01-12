package calc.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import giava.gui.GTextField;
import giava.math.exceptions.MathException;
import giava.gui.GRectangularButton;

public class InputField extends JPanel {
    GTextField number;
    GTextField inBase;
    GTextField outBase;
    JLabel numberLabel;
    JLabel inBaseLabel;
    JLabel outBaseLabel;
    GRectangularButton send;

    public InputField(ActionListener list, Font font){
        setBackground(Color.BLACK);

        number = new GTextField("Number", 20);
        number.setBackground(Color.DARK_GRAY);
        number.setForeground(Color.WHITE);
        number.setFont(font);

        numberLabel = new JLabel("Number:");
        numberLabel.setBackground(Color.DARK_GRAY);
        numberLabel.setForeground(Color.WHITE);
        numberLabel.setFont(font);

        inBase = new GTextField("Input base", 20);
        inBase.setBackground(Color.DARK_GRAY);
        inBase.setForeground(Color.WHITE);
        inBase.setFont(font);

        inBaseLabel = new JLabel("Input base:");
        inBaseLabel.setBackground(Color.DARK_GRAY);
        inBaseLabel.setForeground(Color.WHITE);
        inBaseLabel.setFont(font);

        outBase = new GTextField("Output base", 20);
        outBase.setBackground(Color.DARK_GRAY);
        outBase.setForeground(Color.WHITE);
        outBase.setFont(font);

        outBaseLabel = new JLabel("Output base:");
        outBaseLabel.setBackground(Color.DARK_GRAY);
        outBaseLabel.setForeground(Color.WHITE);
        outBaseLabel.setFont(font);

        send = new GRectangularButton("Calculate");
        send.setFont(new Font("SansSerif", Font.BOLD, 20));
        send.setForeground(Color.WHITE);
        send.setBackground(new Color(255,165,0));
        send.setFocusPainted(false);
        send.addActionListener(list);

        setLayout(new GridLayout(7,1,5,5));
        add(numberLabel);
        add(number);
        add(inBaseLabel);
        add(inBase);
        add(outBaseLabel);
        add(outBase);
        add(send);
    }

    public String getNumber(){
        return number.getText().trim();
    }

    public int getInBase() throws MathException {
        try{
            return Integer.parseInt(inBase.getText().trim());
        }catch(Exception e){
            throw new MathException("Unparsable base");
        }
    }

    public int getOutBase() throws MathException {
        try{
            return Integer.parseInt(outBase.getText().trim());
        }catch(Exception e){
            throw new MathException("Unparsable base");
        }
    }
}