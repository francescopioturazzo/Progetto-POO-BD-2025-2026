package gui;

import controller.Controller;

import javax.swing.*;

public class Home {

    private JPanel mainPanel;

    private JButton btnVeicoli;
    private JButton btnClienti;
    private JButton btnNoleggi;
    private JButton btnScooter;
    private JButton btnPagamento;

    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {

        frameHome = new JFrame("Home - Gestionale Noleggio Veicoli");

        Home schermataHome = new Home();

        frameHome.setContentPane(schermataHome.mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);
    }

    public Home() {

        controller = new Controller();   // Controller unico per tutta l'app

        // Imposta i testi dei pulsanti
        btnVeicoli.setText("Gestione Auto");
        btnClienti.setText("Gestione Clienti");
        btnNoleggi.setText("Gestione Noleggi");
        btnScooter.setText("Gestione Scooter");
        btnPagamento.setText("Gestione Pagamenti");

        // Collega i pulsanti ai rispettivi frame
        btnVeicoli.addActionListener(e -> new AutoGUI(frameHome, controller));
        btnClienti.addActionListener(e -> new ClienteGUI(frameHome, controller));
        btnNoleggi.addActionListener(e -> new NoleggioGUI(frameHome, controller));
        btnScooter.addActionListener(e -> new ScooterGUI(frameHome, controller));
        btnPagamento.addActionListener(e -> new PagamentoGUI(frameHome, controller));
    }
}
