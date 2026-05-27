package gui;

import controller.Controller;
import javax.swing.*;

public class Noleggio {

    private JFrame frame;
    private JPanel mainPanel;

    private JLabel colCliente, colVeicolo, colInizio, colFine, colPrezzo;
    private JLabel r1c1, r1c2, r1c3, r1c4, r1c5;

    private JButton IndietroButton;

    public Noleggio(JFrame frameChiamante, Controller controller){

        frame = new JFrame("Noleggio");
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);

        IndietroButton.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });
    }
}
