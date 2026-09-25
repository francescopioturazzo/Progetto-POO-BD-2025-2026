package gui;

import controller.Controller;
import model.Cliente;
import model.Noleggio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Interfaccia grafica per la gestione dei clienti.
 * Permette di visualizzare, aggiungere, modificare, eliminare
 * e vedere i noleggi associati a un cliente.
 *
 * <p>La finestra mostra una tabella con tutti i clienti e vari pulsanti
 * per eseguire le operazioni principali.</p>
 *
 * <p>Questa classe comunica con il {@link Controller} per ottenere
 * i dati e aggiornare il database.</p>
 *
 * @see model.Cliente
 * @see model.Noleggio
 * @see controller.Controller
 *
 * @author Francesco & Vincenzo
 */
public class ClienteGUI {

    /** Finestra principale della GUI. */
    private JFrame frame;

    /** Pannello principale che contiene tabella e pulsanti. */
    private JPanel juve;

    /** Tabella che mostra l'elenco dei clienti. */
    private JTable tabellaClienti;

    /** Modello della tabella che gestisce i dati dei clienti. */
    private DefaultTableModel modelloTabellaClienti;

    /** Pulsante per aggiungere un nuovo cliente. */
    private JButton pulsanteAggiungiCliente;

    /** Pulsante per modificare un cliente esistente. */
    private JButton pulsanteModificaCliente;

    /** Pulsante per eliminare un cliente. */
    private JButton pulsanteEliminaCliente;

    /** Pulsante per visualizzare i noleggi associati a un cliente. */
    private JButton pulsanteMostraNoleggiCliente;

    /** Pulsante per tornare alla finestra precedente. */
    private JButton pulsanteIndietro;

    /** Controller che gestisce la logica dell'applicazione. */
    private Controller controller;

    /**
     * Costruisce la finestra di gestione dei clienti.
     *
     * @param frameChiamante finestra precedente da cui è stata aperta
     * @param controller controller che gestisce le operazioni
     */
    public ClienteGUI(JFrame frameChiamante, Controller controller) {

        this.controller = controller;

        creaComponentiForm();

        frame = new JFrame("Gestione Clienti");
        frame.setContentPane(juve);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inizializzaTabellaClienti();
        inizializzaPulsanti(frameChiamante);

        aggiornaTabellaClienti();

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
    }

    /**
     * Crea i componenti grafici principali della finestra:
     * tabella e pulsanti.
     */
    private void creaComponentiForm() {

        juve = new JPanel(new BorderLayout(10, 10));

        tabellaClienti = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaClienti);
        juve.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new GridLayout(1, 5, 10, 0));

        pulsanteAggiungiCliente = new JButton("Aggiungi");
        pulsanteModificaCliente = new JButton("Modifica");
        pulsanteEliminaCliente = new JButton("Elimina");
        pulsanteMostraNoleggiCliente = new JButton("Noleggi");
        pulsanteIndietro = new JButton("Indietro");

        panelButtons.add(pulsanteAggiungiCliente);
        panelButtons.add(pulsanteModificaCliente);
        panelButtons.add(pulsanteEliminaCliente);
        panelButtons.add(pulsanteMostraNoleggiCliente);
        panelButtons.add(pulsanteIndietro);

        juve.add(panelButtons, BorderLayout.SOUTH);
    }

    /**
     * Inizializza la tabella dei clienti impostando colonne,
     * altezza righe e modalità di selezione.
     */
    private void inizializzaTabellaClienti() {

        modelloTabellaClienti = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Cognome", "Telefono", "Email"},
                0
        );

        tabellaClienti.setModel(modelloTabellaClienti);
        tabellaClienti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaClienti.setRowHeight(25);
        tabellaClienti.getTableHeader().setReorderingAllowed(false);
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

        pulsanteAggiungiCliente.addActionListener(e -> mostraFinestraAggiungiCliente());

        pulsanteModificaCliente.addActionListener(e -> {
            Cliente cliente = ottieniClienteSelezionato();
            if (cliente != null) {
                mostraFinestraModificaCliente(cliente);
            } else {
                mostraMessaggio("Seleziona un cliente prima di modificarlo.");
            }
        });

        pulsanteEliminaCliente.addActionListener(e -> {
            Cliente cliente = ottieniClienteSelezionato();
            if (cliente != null) {
                controller.deleteCliente(cliente.getId());
                aggiornaTabellaClienti();
            } else {
                mostraMessaggio("Seleziona un cliente prima di eliminarlo.");
            }
        });

        pulsanteMostraNoleggiCliente.addActionListener(e -> {
            Cliente cliente = ottieniClienteSelezionato();
            if (cliente != null) {
                mostraNoleggiCliente(cliente);
            } else {
                mostraMessaggio("Seleziona un cliente prima di vedere i noleggi.");
            }
        });
    }

    /**
     * Aggiorna la tabella dei clienti leggendo i dati dal controller.
     */
    public void aggiornaTabellaClienti() {

        modelloTabellaClienti.setRowCount(0);

        List<Cliente> elencoClienti = controller.getAllClienti();
        if (elencoClienti == null) return;

        for (Cliente cliente : elencoClienti) {
            modelloTabellaClienti.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCognome(),
                    cliente.getTelefono(),
                    cliente.getEmail()
            });
        }
    }

    /**
     * Restituisce il cliente selezionato nella tabella.
     *
     * @return cliente selezionato oppure null se nessuna riga è selezionata
     */
    private Cliente ottieniClienteSelezionato() {

        int rigaSelezionata = tabellaClienti.getSelectedRow();

        if (rigaSelezionata == -1) {
            return null;
        }

        int idCliente = (int) modelloTabellaClienti.getValueAt(rigaSelezionata, 0);

        return controller.getClienteById(idCliente);
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
     * Mostra la finestra per aggiungere un nuovo cliente.
     */
    private void mostraFinestraAggiungiCliente() {

        JDialog dialog = new JDialog(frame, "Aggiungi Cliente", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoNome = new JTextField();
        JTextField campoCognome = new JTextField();
        JTextField campoTelefono = new JTextField();
        JTextField campoEmail = new JTextField();

        panel.add(new JLabel("Nome:"));
        panel.add(campoNome);
        panel.add(new JLabel("Cognome:"));
        panel.add(campoCognome);
        panel.add(new JLabel("Telefono:"));
        panel.add(campoTelefono);
        panel.add(new JLabel("Email:"));
        panel.add(campoEmail);

        JButton btnSalva = new JButton("Salva");
        panel.add(btnSalva);

        dialog.add(panel);

        btnSalva.addActionListener(ev -> {
            try {
                String nome = campoNome.getText();
                String cognome = campoCognome.getText();
                String telefono = campoTelefono.getText();
                String email = campoEmail.getText();

                Cliente nuovoCliente = new Cliente(0, nome, cognome, telefono, email);
                controller.insertCliente(nuovoCliente);

                aggiornaTabellaClienti();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * Mostra la finestra per modificare un cliente esistente.
     *
     * @param cliente cliente da modificare
     */
    private void mostraFinestraModificaCliente(Cliente cliente) {

        JDialog dialog = new JDialog(frame, "Modifica Cliente", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoNome = new JTextField(cliente.getNome());
        JTextField campoCognome = new JTextField(cliente.getCognome());
        JTextField campoTelefono = new JTextField(cliente.getTelefono());
        JTextField campoEmail = new JTextField(cliente.getEmail());

        panel.add(new JLabel("Nome:"));
        panel.add(campoNome);
        panel.add(new JLabel("Cognome:"));
        panel.add(campoCognome);
        panel.add(new JLabel("Telefono:"));
        panel.add(campoTelefono);
        panel.add(new JLabel("Email:"));
        panel.add(campoEmail);

        JButton btnAggiorna = new JButton("Aggiorna");
        panel.add(btnAggiorna);

        dialog.add(panel);

        btnAggiorna.addActionListener(ev -> {
            try {
                String nome = campoNome.getText();
                String cognome = campoCognome.getText();
                String telefono = campoTelefono.getText();
                String email = campoEmail.getText();

                Cliente clienteAggiornato = new Cliente(cliente.getId(), nome, cognome, telefono, email);
                controller.updateCliente(clienteAggiornato);

                aggiornaTabellaClienti();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * Mostra l'elenco dei noleggi associati a un cliente.
     *
     * @param cliente cliente di cui visualizzare i noleggi
     */
    private void mostraNoleggiCliente(Cliente cliente) {

        JDialog dialog = new JDialog(frame, "Noleggi del Cliente", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(frame);

        JTable tabella = new JTable();
        DefaultTableModel modello = new DefaultTableModel(
                new Object[]{"ID", "Veicolo", "Inizio", "Fine", "Prezzo"},
                0
        );

        tabella.setModel(modello);
        tabella.setRowHeight(25);

        List<Noleggio> lista = controller.getAllNoleggi();

        for (Noleggio n : lista) {
            if (n.getIdCliente() == cliente.getId()) {
                modello.addRow(new Object[]{
                        n.getId(),
                        n.getIdVeicolo(),
                        n.getDataInizio(),
                        n.getDataFine(),
                        n.getCosto()
                });
            }
        }

        dialog.add(new JScrollPane(tabella));
        dialog.setVisible(true);
    }
}
