package giava.gui;

import java.awt.*;
import javax.swing.*;

public class GFrame extends JFrame{
    public GFrame(){}
    
    public GFrame(String title){
        super(title);
    }
    
    public GFrame(int h, int w){
        setSize(w,h);
        setVisible(true);
    }
    
    public GFrame(String title, int h, int w){
        super(title);
        setSize(w,h);
        setVisible(true);
    }
    
    public GFrame(int h, int w, int x, int y){
        setSize(w,h);
        setLocation(x,y);
        setVisible(true);
    }
    
    public GFrame(String title, int h, int w, int x, int y){
        super(title);
        setSize(w,h);
        setLocation(x,y);
        setVisible(true);
    }
    
    public GFrame(int h, int w, int x, int y, Color color){
        setSize(w,h);
        setLocation(x,y);
        getContentPane().setBackground(color);
        setVisible(true);
    }
}