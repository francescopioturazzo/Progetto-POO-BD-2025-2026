package gui;

import controller.Controller;
import javax.swing.*;

public class Pagamento {

    private JFrame frame;
    private JPanel pannello2;

    private JLabel colId, colMetodo, colImporto, colData;
    private JLabel r1c1, r1c2, r1c3, r1c4;

    private JButton bottone;

    public Pagamento(JFrame frameChiamante, Controller controller){

        frame = new JFrame("Pagamento");
        frame.setContentPane(pannello2);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameChiamante.setVisible(false);
        frame.setVisible(true);

        bottone.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });
    }
}
