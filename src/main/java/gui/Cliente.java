package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente {

    private JFrame frame;
    private JPanel juve;
    private JButton ciao;


    public Cliente(JFrame frameChiamante, Controller controller){

        frame = new JFrame("cliente");
        frame.setContentPane(juve);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);


        ciao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
