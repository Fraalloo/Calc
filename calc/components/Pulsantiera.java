package calc.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import giava.gui.GCircularButton;

public class Pulsantiera extends JPanel {
    private GCircularButton[] standard;
    private GCircularButton[] scientific;

    private Font numFontStd;
    private Font numFontSci;
    
    boolean std = true;
        
    public Pulsantiera(ActionListener list, Font numFontStd, Font numFontSci){
        setNumFontStd(numFontStd);
        setNumFontSci(numFontSci);
        setBackground(Color.BLACK);
        init(list);
        load();
    }
    
    public void init(ActionListener list){
        String[] standard = {
            "AC", "C", "+/-", "%",
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        this.standard = new GCircularButton[standard.length];
        
        String[] scientific = {
            "AC", "C", "+/-", "%", "md", "7", "8", "9", "÷",
            "sin", "cos", "tan", "π", "e", "4", "5", "6", "×",
            "ln", "log", "n!", "(", ")", "1", "2", "3", "-",
            "√", "x²", "x³", "x^", "exp", "0", ".", "=", "+"
        };
        this.scientific = new GCircularButton[scientific.length];
    
        for(int i = 0; i < standard.length; i++){
            this.standard[i] = new GCircularButton(standard[i]);
            this.standard[i].addActionListener(list);
            this.standard[i].setBackground(defColor(standard[i]));
            this.standard[i].setForeground(Color.WHITE);
            this.standard[i].setFont(numFontStd);
        }
        
        for(int i = 0; i < scientific.length; i++){
            this.scientific[i] = new GCircularButton(scientific[i]);
            this.scientific[i].addActionListener(list);
            this.scientific[i].setBackground(defColor(scientific[i]));
            this.scientific[i].setForeground(Color.WHITE);
            this.scientific[i].setFont(numFontSci);
        }
    }
    
    private Color defColor(String str){
        try{
            Integer.parseInt(str);
        }catch(Exception e){
            if(
                str == "+" ||
                str == "-" ||
                str == "×" ||
                str == "÷" ||
                str == "="
            ) return new Color(255,165,0);
            if(str == ".") return new Color(56,62,66);
            if(
                str == "AC"  ||
                str == "C"   ||
                str == "%"   ||
                str == "+/-" || 
                str == "md"
            ) return Color.GRAY.darker();
            return new Color(55,55,55);
        }
        
        return new Color(56,62,66);
    }
    
    public void load(){
        removeAll();
        setLayout(new GridLayout(std ? 5 : 4, std ? 4 : 9,10,10));
        JButton[] btns = std ? standard : scientific;
        
        for(JButton btn: btns){
            add(btn);
        }
        
        revalidate();
        repaint();
    }
    
    public boolean isStandard(){
        return std;
    }
    
    public boolean isScientific(){
        return !std;
    }
    
    public void toggleType(){
        std = !std;
    }

    public void setNumFontStd(Font numFontStd){
        this.numFontStd = numFontStd;
    }

    public void setNumFontSci(Font numFontSci){
        this.numFontSci = numFontSci;
    }
}