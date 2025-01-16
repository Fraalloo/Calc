package calc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

import calc.components.Display;
import calc.components.InputField;
import calc.components.Pulsantiera;

import giava.gui.GFrame;
import giava.datastr.LinkedList;
import giava.math.parser.Parser;
import giava.math.GMath;
import giava.math.exceptions.MathException;
import giava.math.exceptions.MathSyntaxError;

public class Calcolatrice extends GFrame implements ActionListener {
    private Display disp;
    private Pulsantiera puls;
    private InputField sys;
    private LinkedList<String> expr;
    private boolean debug;
    
    private static final double mul = 500;
    private static final int h_std = (int)(mul * 1.6);
    private static final int w_std = (int)mul;
    private static final int h_sci = (int)(mul * 1.2);
    private static final int w_sci = (int)(mul * 1.7);
    private static final int h_sys = (int)(mul * 1.3);
    private static final int w_sys = (int)mul;

    public Calcolatrice(
        boolean debug,
        Image img,
        Font polyItalic,
        Font ubuntuBold
    ){
        super("Calcolatrice");
        setSize(w_std, h_std);
        expr = new LinkedList<>();
        this.debug = debug;
        setIconImage(img);

        JPanel jp = (JPanel)getContentPane();
        jp.setBackground(new Color(156,156,156));        
        jp.setLayout(new BorderLayout());

        disp = new Display(this, polyItalic.deriveFont(Font.BOLD,24f), ubuntuBold.deriveFont(24f));
        disp.setPreferredSize(new Dimension(w_std, h_std * 1/4));

        puls = new Pulsantiera(this, polyItalic.deriveFont(Font.BOLD,24f), polyItalic.deriveFont(Font.BOLD,20f));
        puls.setPreferredSize(new Dimension(w_std, h_std * 3/4));
        
        sys = new InputField(this, ubuntuBold.deriveFont(24f));
        sys.setPreferredSize(new Dimension(w_std, h_std * 3/4));
        
        jp.add(disp,BorderLayout.NORTH);
        jp.add(puls,BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        
        switch(cmd){
            case "Standard":
                disp.changeTypeButton("Scientific");
                expr.clear();
                setSize(w_sci, h_sci);
                puls.toggleType();
                puls.load();
                break;
            case "Scientific":
                disp.changeTypeButton("Number Systems");
                expr.clear();
                setSize(w_sys, h_sys);
                remove(puls);
                add(sys);
                break;
            case "Number Systems":
                disp.changeTypeButton("Standard");
                expr.clear();
                setSize(w_std, h_std);
                remove(sys);
                add(puls,BorderLayout.CENTER);
                puls.toggleType();
                puls.load();
                break;
            case "AC":
                expr.clear();
                break;
            case "C":
                expr.removeLast();
                break;
            case "+/-":
                if(expr.getFirst().charAt(0) == '-') expr.setFirst(expr.getFirst().substring(1));
                else expr.setFirst("-" + expr.getFirst());
                break;
            case "%":
                expr.addLast("×", "100");
                break;
            case "sin":
            case "cos":
            case "tan":
            case "ln":
            case "log":
            case "√":
                if(
                    expr.size() == 1 &&
                    (
                        GMath.isNumber(expr.getFirst()) ||
                        GMath.isConstant(expr.getFirst())
                    )
                ) expr.addFirst(cmd,"(");
                else expr.addLast(cmd, "(");
                break;
            case "x²":
                expr.addLast("^","2");
                break;
            case "x³":
                expr.addLast("^","3");
                break;
            case "x^":
                expr.addLast("^");
                break;
            case "n!":
                expr.addLast("fact","(");
                break;
            case "exp":
                expr.addLast("e","^");
                break;
            case "=":
                if(expr.isEmpty()) break;
                try{
                    double ast = Parser.parse(expr).evaluate();
                    String ret = ast == Double.POSITIVE_INFINITY ?
                        "∞" :
                        ast == (int)ast ?
                            String.valueOf((int)ast) :
                            String.valueOf(ast);
                    disp.changeExpr(ret);
                    expr.clear();
                    expr.addLast(ret);
                }catch(MathSyntaxError ex){
                    disp.changeExpr(ex.getMessage());
                    expr.clear();
                }
                break;
            case "Calculate":
                expr.clear();
                try{
                    String num = sys.getNumber();
                    int inBase = sys.getInBase();
                    int outBase = sys.getOutBase();

                    if(debug){
                        System.out.println(num);
                        System.out.println(inBase);
                        System.out.println(outBase);
                    }

                    String ret = GMath.convert(num, inBase, outBase);
                    disp.changeExpr(ret);
                    expr.clear();
                    expr.addLast(ret);
                }catch(MathException ex){
                    expr.clear();
                    expr.addLast(ex.getMessage());
                }
                break;
            default: 
                String last = expr.getLast();
                String prelast = expr.get(expr.size() - 2);

                if(last == null) expr.addLast(cmd);
                else if(
                    (
                        GMath.isNumber(cmd) ||
                        cmd.endsWith(".")
                    ) && 
                    !expr.isEmpty() &&
                    (
                        GMath.isNumber(last) ||
                        last.endsWith(".")
                    ) || 
                    (
                        last.equals("-") && 
                        (prelast == null || GMath.isNumber(prelast))
                    )
                ) expr.setLast(last + cmd);
                else expr.addLast(cmd);
        }

        if(cmd != "=") disp.changeExpr(expr.toString("","",""));
        if(debug) System.out.println(expr);
    }
}