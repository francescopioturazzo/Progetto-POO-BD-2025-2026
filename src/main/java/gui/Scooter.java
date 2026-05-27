package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scooter {

    private JFrame frame;
    private JPanel pannelo1;
    private JButton bottone2;


    public Scooter(JFrame frameChiamante, Controller controller){

        frame = new JFrame("scooter");
        frame.setContentPane(pannelo1);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);


        bottone2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
