package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Target {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Target window = new Target();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Target() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 529, 377);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        JPanel pnlControl = new JPanel();
        pnlControl.setBounds(10, 32, 157, 280);
        frame.getContentPane().add(pnlControl);
        pnlControl.setLayout(null);

        JTextArea broi = new JTextArea();
        broi.setBounds(26, 40, 78, 23);
        pnlControl.add(broi);

        JLabel lblIntervals = new JLabel("Интервали");
        lblIntervals.setBounds(26, 16, 72, 14);
        pnlControl.add(lblIntervals);

        JPanel pnlCanvas = new JPanel();
        pnlCanvas.setBounds(189, 32, 280, 280);
        frame.getContentPane().add(pnlCanvas);

        JTextArea raz = new JTextArea();
        raz.setBounds(26, 94, 78, 23);
        pnlControl.add(raz);

        JTextArea diam = new JTextArea();
        diam.setBounds(26, 139, 78, 23);
        pnlControl.add(diam);

        JButton btnDraw = new JButton("Draw");
        btnDraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Graphics g=pnlCanvas.getGraphics();
                int d = 0, n = 0, m = 0;
                try {
                    d = Integer.parseInt(diam.getText()); // Минимален диаметър //
                    n = Integer.parseInt(broi.getText()); // Поле (Или предполагаемия брой на кръговете) Не знам //
                    m = Integer.parseInt(raz.getText()); // Отстоянието //
                }
                catch (NumberFormatException ex)
                {
                    showMessageDialog(null, "Не е въведено валидно число", "Грешка", WARNING_MESSAGE);
                }
                int w = pnlCanvas.getWidth(); // Широчината на панела //
                int h = pnlCanvas.getHeight(); // Височината на панела //
                d = d +m*n/2; // Намира диаметъра на най-големия кръг //

                int x = w/2 - m/4*n; // X на най-външния кръг //
                int y = h/2 - m/4*n; // Y на най-външния кръг //
                boolean clr = false; // Задаването на цветовете //
                Color green = new Color(82, 145, 88); // Зелен кръг //
                Color white = new Color(255, 255, 255); // Бял Кръг //
                for(int i = 0; i < n; i++)
                {
                    if(clr == true)
                    {
                        g.setColor(green);
                        clr = false;
                    } else
                    {
                        g.setColor(white);
                        clr = true;
                    }
                    g.fillOval(x, y, d, d);
                    x = x + m/2;
                    y = y + m/2;
                    d = d - m;
                }


            }
        });
        btnDraw.setBounds(0, 185, 72, 23);
        pnlControl.add(btnDraw);

        JButton btnNewButton = new JButton("Exit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton.setBounds(77, 185, 70, 23);
        pnlControl.add(btnNewButton);


        JLabel otstoqnie = new JLabel("Отстояние");
        otstoqnie.setBounds(26, 74, 65, 14);
        pnlControl.add(otstoqnie);

        JLabel lblCount = new JLabel("Мин. диаметър");
        lblCount.setBounds(26, 124, 106, 14);
        pnlControl.add(lblCount);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pnlCanvas.repaint();
            }
        });
        btnClear.setBounds(26, 219, 93, 23);
        pnlControl.add(btnClear);
    }
}


