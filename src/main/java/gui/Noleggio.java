package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Noleggio {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton IndietroButton;


    public Noleggio(JFrame frameChiamante, Controller controller){

        frame = new JFrame("Manutenzione");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);


        IndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
