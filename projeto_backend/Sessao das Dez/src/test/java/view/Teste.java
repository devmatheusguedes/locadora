package view;

import javax.swing.*;
import java.awt.*;

public class Teste extends JFrame {
    public static void main(String[] args) {
        Teste teste = new Teste();
    }
    public Teste(){
        this.setLayout(null);
        this.setSize(800, 600);
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, this.getWidth(), this.getHeight());
        p1.setBackground(new Color(200));


        JPanel p2 = new JPanel();
        p2.setBackground(new Color(200, 0, 0));
        //p2.setBounds(50, 0, 200, 40);

        p1.setLayout(new GridLayout(2, 1));
        //p1.add(p2, BorderLayout.CENTER);
        p1.add(new JButton("teeste"));
        p1.add(new JButton("teeste2"));
       // p1.add(new JButton("teeste3"), BorderLayout.SOUTH);


        this.setLocationRelativeTo(null);
        this.add(p1);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
