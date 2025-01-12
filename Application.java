import java.io.IOException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import calc.Calcolatrice;

public class Application{
    public static void main(String args[]){
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }

        Font polyItalic;
        try{
            polyItalic = Font.createFont(Font.TRUETYPE_FONT, Application.class.getResourceAsStream("assets/fonts/Poly/Poly-Italic.ttf"));
        }catch(FontFormatException|IOException e){
            e.printStackTrace();
            polyItalic = new Font("DejaVu Serif", Font.BOLD, 20);
        }

        Font ubuntuBold;
        try{
            ubuntuBold = Font.createFont(Font.TRUETYPE_FONT, Application.class.getResourceAsStream("assets/fonts/Ubuntu/Ubuntu-Bold.ttf"));
        }catch(FontFormatException|IOException e){
            e.printStackTrace();
            ubuntuBold = new Font("SansSerif", Font.BOLD, 20);
        }

        final Font dialogFont = ubuntuBold;

        Image img;
        try{
            img = new ImageIcon(Application.class.getResource("assets/imgs/icon.png")).getImage();
        }catch(NullPointerException e){
            e.printStackTrace();
            img = null;
        }

        Calcolatrice calc = new Calcolatrice(
            true,
            img,
            polyItalic,
            ubuntuBold
        );

        calc.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                JLabel label = new JLabel("<html>Grazie per aver utilizzato<br>la Calcolatrice</html>");
                label.setForeground(Color.WHITE);
                label.setBackground(Color.BLACK);
                label.setFont(dialogFont.deriveFont(16f));
                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER);

                JDialog dialog = new JDialog(calc, "Uscita in corso...", true);
                dialog.setSize(200, 100);
                dialog.getContentPane().setBackground(Color.BLACK);
                dialog.setLayout(new FlowLayout());
                dialog.add(label);
                dialog.setLocationRelativeTo(calc);
                dialog.setResizable(false);

                Timer timer = new Timer(3000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        dialog.dispose();
                        System.exit(0);
                    }
                });

                timer.setRepeats(false);
                timer.start();
                dialog.setVisible(true);
            }
        });
    }
}