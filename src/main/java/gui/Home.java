package gui;

import controller.Controller;

import javax.swing.*;

/**
 * Schermata principale dell'applicazione.
 * Permette di accedere alle varie sezioni del gestionale:
 * auto, clienti, noleggi, scooter e pagamenti.
 *
 * <p>Questa classe rappresenta il punto di ingresso della GUI
 * e crea un unico {@link Controller} condiviso da tutte le schermate.</p>
 *
 * @see gui.AutoGUI
 * @see gui.ClienteGUI
 * @see gui.NoleggioGUI
 * @see gui.ScooterGUI
 * @see gui.PagamentoGUI
 * @see controller.Controller
 */
public class Home {

    /** Pannello principale della schermata Home. */
    private JPanel mainPanel;

    /** Pulsante per aprire la gestione delle auto. */
    private JButton btnVeicoli;

    /** Pulsante per aprire la gestione dei clienti. */
    private JButton btnClienti;

    /** Pulsante per aprire la gestione dei noleggi. */
    private JButton btnNoleggi;

    /** Pulsante per aprire la gestione degli scooter. */
    private JButton btnScooter;

    /** Pulsante per aprire la gestione dei pagamenti. */
    private JButton btnPagamento;

    /** Finestra principale dell'applicazione. */
    private static JFrame frameHome;

    /** Controller condiviso da tutte le schermate. */
    private Controller controller;

    /**
     * Metodo principale che avvia l'applicazione.
     *
     * @param args argomenti da linea di comando (non utilizzati)
     */
    public static void main(String[] args) {

        frameHome = new JFrame("Home - Gestionale Noleggio Veicoli");

        Home schermataHome = new Home();

        frameHome.setContentPane(schermataHome.mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);
    }

    /**
     * Costruttore della schermata Home.
     * Inizializza il controller e collega i pulsanti alle varie GUI.
     *
     * @author Francesco & Vincenzo
     */
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
