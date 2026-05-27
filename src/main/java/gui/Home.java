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
    private JButton btnPagamenti;
    private JButton btnManutenzioni;

    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        frameHome = new JFrame("Home - Gestionale Noleggio Veicoli");
        frameHome.setContentPane(new Home().mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setLocationRelativeTo(null);
        frameHome.setVisible(true);
    }

    public Home() {
        controller = new Controller();

        // Listener pulsante VEICOLI
        btnVeicoli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriVeicoli();
            }
        });

        // Listener pulsante CLIENTI
        btnClienti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriClienti();
            }
        });

        // Listener pulsante NOLEGGI
        btnNoleggi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriNoleggi();
            }
        });

        // Listener pulsante PAGAMENTI
        btnPagamenti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriPagamenti();
            }
        });

        // Listener pulsante MANUTENZIONI
        btnManutenzioni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriManutenzioni();
            }
        });
    }

    // -------------------------------
    // METODI DI APERTURA DELLE FINESTRE
    // -------------------------------

    private void apriVeicoli() {
        JOptionPane.showMessageDialog(frameHome, "Apertura gestione Veicoli...");
        // new VeicoliGUI(controller);
    }

    private void apriClienti() {
        JOptionPane.showMessageDialog(frameHome, "Apertura gestione Clienti...");
        // new ClientiGUI(controller);
    }

    private void apriNoleggi() {
        JOptionPane.showMessageDialog(frameHome, "Apertura gestione Noleggi...");
        // new NoleggiGUI(controller);
    }

    private void apriPagamenti() {
        JOptionPane.showMessageDialog(frameHome, "Apertura gestione Pagamenti...");
        // new PagamentiGUI(controller);
    }

    private void apriManutenzioni() {
        JOptionPane.showMessageDialog(frameHome, "Apertura gestione Manutenzioni...");
        // new ManutenzioniGUI(controller);
    }
}
