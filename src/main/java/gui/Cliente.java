package gui;

import controller.Controller;
import javax.swing.*;

public class Cliente {

    private JFrame frame;
    private JPanel juve;

    private JLabel colNome, colCognome, colPatente, colEmail;
    private JLabel r1c1, r1c2, r1c3, r1c4;

    private JButton ciao;

    public Cliente(JFrame frameChiamante, Controller controller){

        frame = new JFrame("Cliente");
        frame.setContentPane(juve);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);

        ciao.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });
    }
}
