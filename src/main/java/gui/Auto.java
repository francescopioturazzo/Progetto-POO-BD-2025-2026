package gui;

import controller.Controller;
import javax.swing.*;

public class Auto {

    private JFrame frame;
    private JPanel napoli;

    private JLabel colId, colTarga, colMarca, colModello, colPorte, colAlim, colStato;
    private JLabel r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7;

    private JButton back;

    public Auto(JFrame frameChiamante, Controller controller){

        frame = new JFrame("Auto");
        frame.setContentPane(napoli);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);

        back.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });
    }
}
