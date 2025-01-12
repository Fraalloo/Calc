package giava.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class GTextField extends JTextField implements FocusListener {
    private String placeholder;
    private Font font;

    {
        addFocusListener(this);
    }

    public GTextField(){}
    
    public GTextField(String text){
        super(text);
        placeholder = text;
    }

    public GTextField(String text, int col){
        super(text, col);
        placeholder = text;
    }

    @Override
    public void setFont(Font font){
        this.font = font;
        super.setFont(new Font(font.getFamily(), Font.ITALIC, font.getSize()));
    }

    private void swapFont(){
        Font temp = font;
        font = getFont();
        super.setFont(temp);
    }

    @Override
    protected void paintBorder(Graphics g){
        g.setColor(getBackground());
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void focusGained(FocusEvent e){
        if(getText().equals(placeholder)){
            setText("");
            swapFont();
        }
    }

    @Override
    public void focusLost(FocusEvent e){
        if(getText().isEmpty()){
            setText(placeholder);
            swapFont();
        }
    }
}
