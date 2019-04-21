package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Triangles {
    private JFrame window = new JFrame("Triangle");
    private JPanel controlPanel;
    private JPanel canvasPanel;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Triangles triangle = new Triangles();
                    triangle.window.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    Triangles()
    {
        Gui();
        centreWindow();
        ControlPanel();
        CanvasPanel();
    }

    private void Gui()
    {
        window.getContentPane().setLayout(null);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 400);
    }

    private void centreWindow()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - window.getWidth())/2);
        int y = (int) ((dimension.getHeight() - window.getHeight())/2);
        window.setLocation(x,y);
    }

    private void ControlPanel()
    {
        controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(10,10, 180, 350);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        window.getContentPane().add(controlPanel);

        JLabel sideLabel = new JLabel("Размер на страната");
        sideLabel.setBounds(35, 5, 150, 23);
        controlPanel.add(sideLabel);

        JTextField sideField = new JTextField();
        sideField.setBounds(10,30,160,23);
        sideField.setHorizontalAlignment(JTextField.CENTER);
        controlPanel.add(sideField);

        JLabel coordOneLabel = new JLabel("Координати за X");
        coordOneLabel.setBounds(40, 65, 150, 23);
        controlPanel.add(coordOneLabel);

        JTextField coord_one = new JTextField();
        coord_one.setBounds(10,90,160, 23);
        coord_one.setHorizontalAlignment(JTextField.CENTER);
        controlPanel.add(coord_one);

        JLabel coordTwoLabel = new JLabel("Координати за Y");
        coordTwoLabel.setBounds(40, 125, 150, 23);
        controlPanel.add(coordTwoLabel);

        JTextField coord_two = new JTextField();
        coord_two.setBounds(10,150,160, 23);
        coord_two.setHorizontalAlignment(JTextField.CENTER);
        controlPanel.add(coord_two);

        JLabel countTrianglesLabel = new JLabel("Брой на триъгълниците");
        countTrianglesLabel.setBounds(20,185,150,23);
        controlPanel.add(countTrianglesLabel);

        JTextField countTrianglesField = new JTextField();
        countTrianglesField.setBounds(10,210,160,23);
        countTrianglesField.setHorizontalAlignment(JTextField.CENTER);
        controlPanel.add(countTrianglesField);


        JButton drawButton = new JButton("Рисувай");
        drawButton.setBounds(40,250, 100, 23);
        controlPanel.add(drawButton);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = canvasPanel.getGraphics();

                int sideInt = 0, x0 = 0, y0 = 0, countTriangles = 0;
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                try {
                    sideInt = Integer.parseInt(sideField.getText());
                    x0 = Integer.parseInt(coord_one.getText());
                    y0 = Integer.parseInt(coord_two.getText());
                    countTriangles = Integer.parseInt(countTrianglesField.getText());
                }
                catch (NumberFormatException e1)
                {
                    showMessageDialog(null, "Невалидни данни", "Грешка", JOptionPane.WARNING_MESSAGE);
                }

                int sideSize = 1;
                boolean first = true;
                while(countTriangles != 0)
                {
                    sideSize *= 2;
                    if(countTriangles % 2 != 0) {
                        if(first)
                        {
                            first = false;
                            x1 = x0 + (sideInt / (sideSize / 2));
                            y1 = y0;
                            x2 = x1 - (sideInt / sideSize);
                            y2 = y1 + (int) (((Math.sqrt((Math.pow(sideInt, 2)) - (Math.pow(sideInt / 2, 2)))) / sideSize) * 2);
                        }
                        else {
                            x0 = x0 + (sideInt / sideSize);
                            y0 = y0 - (int) (((Math.sqrt((Math.pow(sideInt, 2)) - (Math.pow(sideInt / 2, 2)))) / sideSize) * 2);
                            x1 = x0 + (sideInt / (sideSize / 2));
                            y1 = y0;
                            x2 = x1 - (sideInt / sideSize);
                            y2 = y1 + (int) (((Math.sqrt((Math.pow(sideInt, 2)) - (Math.pow(sideInt / 2, 2)))) / sideSize) * 2);
                        }
                    }
                    else{
                        if(first)
                        {
                            first = false;
                            x1 = x0 + (sideInt / sideSize);
                            y1 = y0 - (int)(((Math.sqrt((Math.pow(sideInt,2)) - (Math.pow(sideInt/2,2))))/sideSize)*2);
                            x2 = x0 + (sideInt / (sideSize/2));
                            y2 = y0;
                        }
                        else{
                        x0 = x0 + (sideInt / sideSize);
                        y0 = y0 + (int)(((Math.sqrt((Math.pow(sideInt,2)) - (Math.pow(sideInt/2,2))))/sideSize)*2);
                        x1 = x0 + (sideInt / sideSize);
                        y1 = y0 - (int)(((Math.sqrt((Math.pow(sideInt,2)) - (Math.pow(sideInt/2,2))))/sideSize)*2);
                        x2 = x0 + (sideInt / (sideSize/2));
                        y2 = y0;}
                    }

                    g.drawLine(x0,y0,x1,y1);
                    g.drawLine(x1,y1,x2,y2);
                    g.drawLine(x2,y2,x0,y0);
                    countTriangles--;
                }
            }
        });

        JButton refreshButton = new JButton("Опресни");
        refreshButton.setBounds(40, 280, 100, 23);
        controlPanel.add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasPanel.repaint();
            }
        });

        JButton exitButton = new JButton("Изход");
        exitButton.setBounds(40, 310, 100, 23);
        controlPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });
    }

    private void CanvasPanel(){
        canvasPanel = new JPanel();
        canvasPanel.setBounds(200,10, 280, 350);
        canvasPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        canvasPanel.setBackground(Color.LIGHT_GRAY);
        window.getContentPane().add(canvasPanel);
    }
}

