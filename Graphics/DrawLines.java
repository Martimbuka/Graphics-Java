package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class DrawLines {
    public static void main(String[] args)
    {
        //JFrame
        JFrame window = new JFrame("Lines");
        window.setSize(500, 300);
        window.setResizable(false);
        window.getContentPane().setLayout(null);

        //Panel Control
        JPanel panelControl = new JPanel();
        panelControl.setBounds(0,0,225,262);
        window.getContentPane().add(panelControl);
        panelControl.setLayout(null);

        //Panel Canvas
        JPanel panelCanvas = new JPanel();
        panelCanvas.setBounds(220,0,275,271);
        window.getContentPane().add(panelCanvas);

        //JLabel
        JLabel intervals = new JLabel("Интервали");
        intervals.setBounds(73, 45, 150, 23);
        panelControl.add(intervals);

        //JTextField
        JTextField textField = new JTextField();
        textField.setBounds(30,70,150,23);
        textField.setHorizontalAlignment(JTextField.CENTER);
        panelControl.add(textField);

        //Button "Draw"
        JButton buttonDraw = new JButton("Draw");
        buttonDraw.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                Graphics g = panelCanvas.getGraphics();
                int w = panelCanvas.getWidth();
                int h = panelCanvas.getHeight();
                int numLines = 0;
                try{
                 numLines = Integer.parseInt(textField.getText());}
                catch (NumberFormatException ex)
                {
                    showMessageDialog(null, "Не е въведено валидно число", "Грешка", WARNING_MESSAGE);
                }
                double dx = (double) w / numLines;
                double dy = (double) h / numLines;
                double x = 0;
                double y = h;
                Color color = new Color(230, 4, 0);
                g.setColor(color);
                for(int i = 0; i <= numLines; i++)
                {
                    if(numLines == 0)break;
                    g.drawLine(0,0, (int) x, (int) y);
                    g.drawLine(w, h, (int) x, (int) y);
                    x += dx;
                    y -= dy;
                }
            }
        });
        buttonDraw.setBounds(10,185,64,23);
        panelControl.add(buttonDraw);

        //Button "Reset"
        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCanvas.revalidate();
                panelCanvas.repaint();
                textField.setText("");
            }
        });
        buttonReset.setBounds(80, 185, 70,23);
        panelControl.add(buttonReset);

        //Button "Exit"
        JButton buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });
        buttonExit.setBounds(155,185,55,23);
        panelControl.add(buttonExit);

        window.setVisible(true);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }
}

