package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pagamento {

    private JFrame frame;
    private JPanel pannello2;
    private JButton bottone;


    public Pagamento(JFrame frameChiamante, Controller controller){

        frame = new JFrame("pagamento");
        frame.setContentPane(pannello2);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);


        bottone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
