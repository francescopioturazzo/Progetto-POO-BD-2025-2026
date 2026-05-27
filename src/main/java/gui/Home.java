package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel mainPanel;

    // Pulsanti collegati dal .form
    private JButton btnVeicoli;
    private JButton btnClienti;
    private JButton btnNoleggi;
    private JButton btnScooter;
    private JButton btnPagamento;

    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        frameHome = new JFrame("Home - Gestionale Noleggio Veicoli");
        frameHome.setContentPane(new Home().mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);
    }

    public Home() {
        controller = new Controller();

        // Listener pulsante VEICOLI
        btnVeicoli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Auto(frameHome, controller);
            }
        });

        // Listener pulsante CLIENTI
        btnClienti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cliente(frameHome, controller);
            }
        });

        // Listener pulsante NOLEGGI
        btnNoleggi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Noleggio(frameHome, controller);
            }
        });

        // Listener pulsante SCOOTER
        btnScooter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Scooter(frameHome, controller);
            }
        });
        // Listener pulsante pagamento
        btnPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Pagamento(frameHome, controller);
            }
        });



    }

}

