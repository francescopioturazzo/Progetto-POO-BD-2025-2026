package gui;

import controller.Controller;
import javax.swing.*;

public class Scooter {

    private JFrame frame;
    private JPanel pannelo1;

    private JLabel colId, colTarga, colMarca, colModello, colCilindrata, colAlim, colStato;
    private JLabel r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7;

    private JButton bottone2;

    public Scooter(JFrame frameChiamante, Controller controller){

        frame = new JFrame("Scooter");
        frame.setContentPane(pannelo1);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);

        bottone2.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });
    }
}
