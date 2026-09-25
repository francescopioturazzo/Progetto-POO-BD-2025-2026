package gui;

import controller.Controller;
import model.Noleggio;
import model.Cliente;
import model.Auto;
import model.Scooter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Interfaccia grafica per la gestione dei noleggi.
 * Permette di visualizzare, aggiungere, modificare, eliminare
 * e consultare i dettagli di clienti e veicoli collegati ai noleggi.
 *
 * <p>La finestra mostra una tabella con tutti i noleggi e vari pulsanti
 * per eseguire le operazioni principali.</p>
 *
 * <p>Questa classe comunica con il {@link Controller} per ottenere
 * i dati e aggiornare il database.</p>
 *
 * @see model.Noleggio
 * @see model.Cliente
 * @see model.Auto
 * @see model.Scooter
 * @see controller.Controller
 *
 * @author Francesco & Vincenzo
 */
public class NoleggioGUI {

    /** Finestra principale della GUI. */
    private JFrame frame;

    /** Pannello principale che contiene tabella e pulsanti. */
    private JPanel mainPanel;

    /** Tabella che mostra l'elenco dei noleggi. */
    private JTable tabellaNoleggi;

    /** Modello della tabella che gestisce i dati dei noleggi. */
    private DefaultTableModel modelloTabellaNoleggi;

    /** Pulsante per aggiungere un nuovo noleggio. */
    private JButton pulsanteAggiungiNoleggio;

    /** Pulsante per modificare un noleggio esistente. */
    private JButton pulsanteModificaNoleggio;

    /** Pulsante per eliminare un noleggio. */
    private JButton pulsanteEliminaNoleggio;

    /** Pulsante per visualizzare il cliente associato al noleggio. */
    private JButton pulsanteMostraCliente;

    /** Pulsante per visualizzare il veicolo associato al noleggio. */
    private JButton pulsanteMostraVeicolo;

    /** Pulsante per tornare alla finestra precedente. */
    private JButton pulsanteIndietro;

    /** Controller che gestisce la logica dell'applicazione. */
    private Controller controller;

    /** ID del veicolo usato per filtrare i noleggi (opzionale). */
    private int idVeicolo = -1;

    /**
     * Costruttore principale della schermata Noleggio.
     *
     * @param frameChiamante finestra precedente
     * @param controller controller che gestisce le operazioni
     */
    public NoleggioGUI(JFrame frameChiamante, Controller controller) {
        this.controller = controller;

        creaComponentiForm();

        frame = new JFrame("Gestione Noleggi");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inizializzaTabellaNoleggi();
        inizializzaPulsanti(frameChiamante);

        aggiornaTabellaNoleggi();

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
    }

    /**
     * Costruttore alternativo che mostra solo i noleggi
     * relativi a un determinato veicolo.
     *
     * @param frameChiamante finestra precedente
     * @param controller controller dell'applicazione
     * @param idVeicolo id del veicolo da filtrare
     */
    public NoleggioGUI(JFrame frameChiamante, Controller controller, int idVeicolo) {
        this.controller = controller;
        this.idVeicolo = idVeicolo;

        creaComponentiForm();

        frame = new JFrame("Noleggio Veicolo " + idVeicolo);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inizializzaTabellaNoleggi();
        inizializzaPulsanti(frameChiamante);

        aggiornaTabellaNoleggiFiltrata();

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
    }

    /**
     * Crea i componenti grafici principali della finestra:
     * tabella e pulsanti.
     */
    private void creaComponentiForm() {
        mainPanel = new JPanel(new BorderLayout(10, 10));

        tabellaNoleggi = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaNoleggi);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new GridLayout(1, 6, 10, 0));

        pulsanteAggiungiNoleggio = new JButton("Aggiungi");
        pulsanteModificaNoleggio = new JButton("Modifica");
        pulsanteEliminaNoleggio = new JButton("Elimina");
        pulsanteMostraCliente = new JButton("Cliente");
        pulsanteMostraVeicolo = new JButton("Veicolo");
        pulsanteIndietro = new JButton("Indietro");

        panelButtons.add(pulsanteAggiungiNoleggio);
        panelButtons.add(pulsanteModificaNoleggio);
        panelButtons.add(pulsanteEliminaNoleggio);
        panelButtons.add(pulsanteMostraCliente);
        panelButtons.add(pulsanteMostraVeicolo);
        panelButtons.add(pulsanteIndietro);

        mainPanel.add(panelButtons, BorderLayout.SOUTH);
    }

    /**
     * Inizializza la tabella dei noleggi impostando colonne,
     * altezza righe e modalità di selezione.
     */
    private void inizializzaTabellaNoleggi() {
        modelloTabellaNoleggi = new DefaultTableModel(
                new Object[]{"ID", "ID Cliente", "ID Veicolo", "Inizio", "Fine", "Prezzo"},
                0
        );

        tabellaNoleggi.setModel(modelloTabellaNoleggi);
        tabellaNoleggi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaNoleggi.setRowHeight(25);
        tabellaNoleggi.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Inizializza i pulsanti e assegna le azioni da eseguire
     * quando vengono premuti.
     *
     * @param frameChiamante finestra precedente
     */
    private void inizializzaPulsanti(JFrame frameChiamante) {

        pulsanteIndietro.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });

        pulsanteAggiungiNoleggio.addActionListener(e -> mostraFinestraAggiungiNoleggio());

        pulsanteModificaNoleggio.addActionListener(e -> {
            Noleggio n = ottieniNoleggioSelezionato();
            if (n != null) {
                mostraFinestraModificaNoleggio(n);
            } else {
                mostraMessaggio("Seleziona un noleggio prima di modificarlo.");
            }
        });

        pulsanteEliminaNoleggio.addActionListener(e -> {
            Noleggio n = ottieniNoleggioSelezionato();
            if (n != null) {
                controller.deleteNoleggio(n.getId());
                aggiornaTabellaNoleggiFiltrata();
            } else {
                mostraMessaggio("Seleziona un noleggio prima di eliminarlo.");
            }
        });

        pulsanteMostraCliente.addActionListener(e -> {
            Noleggio n = ottieniNoleggioSelezionato();
            if (n != null) {
                mostraCliente(n);
            } else {
                mostraMessaggio("Seleziona un noleggio prima di vedere il cliente.");
            }
        });

        pulsanteMostraVeicolo.addActionListener(e -> {
            Noleggio n = ottieniNoleggioSelezionato();
            if (n != null) {
                mostraVeicolo(n);
            } else {
                mostraMessaggio("Seleziona un noleggio prima di vedere il veicolo.");
            }
        });
    }

    /**
     * Aggiorna la tabella dei noleggi leggendo tutti i dati dal controller.
     */
    public void aggiornaTabellaNoleggi() {
        modelloTabellaNoleggi.setRowCount(0);

        List<Noleggio> elencoNoleggi = controller.getAllNoleggi();
        if (elencoNoleggi == null) return;

        for (Noleggio n : elencoNoleggi) {
            modelloTabellaNoleggi.addRow(new Object[]{
                    n.getId(),
                    n.getIdCliente(),
                    n.getIdVeicolo(),
                    n.getDataInizioString(),
                    n.getDataFineString(),
                    n.getCosto()
            });
        }
    }

    /**
     * Aggiorna la tabella mostrando solo i noleggi relativi
     * al veicolo indicato.
     */
    public void aggiornaTabellaNoleggiFiltrata() {
        modelloTabellaNoleggi.setRowCount(0);

        List<Noleggio> elencoNoleggi = controller.getAllNoleggi();
        if (elencoNoleggi == null) return;

        for (Noleggio n : elencoNoleggi) {
            if (n.getIdVeicolo() == idVeicolo) {
                modelloTabellaNoleggi.addRow(new Object[]{
                        n.getId(),
                        n.getIdCliente(),
                        n.getIdVeicolo(),
                        n.getDataInizioString(),
                        n.getDataFineString(),
                        n.getCosto()
                });
            }
        }
    }

    /**
     * Restituisce il noleggio selezionato nella tabella.
     *
     * @return noleggio selezionato oppure null se nessuna riga è selezionata
     */
    private Noleggio ottieniNoleggioSelezionato() {
        int riga = tabellaNoleggi.getSelectedRow();
        if (riga == -1) return null;

        int idNoleggio = (int) modelloTabellaNoleggi.getValueAt(riga, 0);
        return controller.getNoleggioById(idNoleggio);
    }

    /**
     * Mostra un messaggio informativo all'utente.
     *
     * @param messaggio testo da mostrare
     */
    private void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(frame, messaggio);
    }

    /**
     * Mostra la finestra per aggiungere un nuovo noleggio.
     */
    private void mostraFinestraAggiungiNoleggio() {

        JDialog dialog = new JDialog(frame, "Aggiungi Noleggio", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoIdCliente = new JTextField();
        JTextField campoInizio = new JTextField();
        JTextField campoFine = new JTextField();
        JTextField campoPrezzo = new JTextField();

        panel.add(new JLabel("ID Cliente:"));
        panel.add(campoIdCliente);
        panel.add(new JLabel("Data Inizio (yyyy-MM-dd):"));
        panel.add(campoInizio);
        panel.add(new JLabel("Data Fine (yyyy-MM-dd):"));
        panel.add(campoFine);
        panel.add(new JLabel("Prezzo:"));
        panel.add(campoPrezzo);

        JButton btnSalva = new JButton("Salva");
        panel.add(btnSalva);

        dialog.add(panel);

        btnSalva.addActionListener(ev -> {
            try {
                int idCliente = Integer.parseInt(campoIdCliente.getText());
                String inizio = campoInizio.getText();
                String fine = campoFine.getText();
                double prezzo = Double.parseDouble(campoPrezzo.getText());

                Noleggio nuovo = new Noleggio(0, idCliente, idVeicolo, inizio, fine, prezzo);
                controller.insertNoleggio(nuovo);

                aggiornaTabellaNoleggiFiltrata();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * Mostra la finestra per modificare un noleggio esistente.
     *
     * @param n noleggio da modificare
     */
    private void mostraFinestraModificaNoleggio(Noleggio n) {

        JDialog dialog = new JDialog(frame, "Modifica Noleggio", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoIdCliente = new JTextField(String.valueOf(n.getIdCliente()));
        JTextField campoInizio = new JTextField(n.getDataInizioString());
        JTextField campoFine = new JTextField(n.getDataFine() == null ? "" : n.getDataFineString());
        JTextField campoPrezzo = new JTextField(String.valueOf(n.getCosto()));

        panel.add(new JLabel("ID Cliente:"));
        panel.add(campoIdCliente);
        panel.add(new JLabel("Data Inizio:"));
        panel.add(campoInizio);
        panel.add(new JLabel("Data Fine:"));
        panel.add(campoFine);
        panel.add(new JLabel("Prezzo:"));
        panel.add(campoPrezzo);

        JButton btnAggiorna = new JButton("Aggiorna");
        panel.add(btnAggiorna);

        dialog.add(panel);

        btnAggiorna.addActionListener(ev -> {
            try {
                int idCliente = Integer.parseInt(campoIdCliente.getText());
                String inizio = campoInizio.getText();
                String fine = campoFine.getText();
                double prezzo = Double.parseDouble(campoPrezzo.getText());

                Noleggio aggiornato = new Noleggio(n.getId(), idCliente, idVeicolo, inizio, fine, prezzo);
                controller.updateNoleggio(aggiornato);

                aggiornaTabellaNoleggiFiltrata();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * Mostra i dettagli del cliente associato al noleggio.
     *
     * @param n noleggio selezionato
     */
    private void mostraCliente(Noleggio n) {
        Cliente c = controller.getClienteById(n.getIdCliente());

        JOptionPane.showMessageDialog(frame,
                "ID Cliente: " + c.getId() +
                        "\nNome: " + c.getNome() +
                        "\nCognome: " + c.getCognome() +
                        "\nTelefono: " + c.getTelefono() +
                        "\nEmail: " + c.getEmail(),
                "Dettagli Cliente",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mostra i dettagli del veicolo associato al noleggio.
     *
     * @param n noleggio selezionato
     */
    private void mostraVeicolo(Noleggio n) {

        Auto auto = controller.getAutoById(n.getIdVeicolo());
        if (auto != null) {
            JOptionPane.showMessageDialog(frame,
                    "ID Veicolo: " + auto.getId() +
                            "\nTarga: " + auto.getTarga() +
                            "\nMarca: " + auto.getMarca() +
                            "\nModello: " + auto.getModello() +
                            "\nPorte: " + auto.getPorte() +
                            "\nStato: " + auto.getStato(),
                    "Dettagli Auto",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Scooter scooter = controller.getScooterById(n.getIdVeicolo());
        if (scooter != null) {
            JOptionPane.showMessageDialog(frame,
                    "ID Veicolo: " + scooter.getId() +
                            "\nTarga: " + scooter.getTarga() +
                            "\nMarca: " + scooter.getMarca() +
                            "\nModello: " + scooter.getModello() +
                            "\nStato: " + scooter.getStato(),
                    "Dettagli Scooter",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
