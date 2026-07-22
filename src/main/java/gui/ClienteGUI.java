package gui;

import controller.Controller;
import model.Cliente;
import model.Noleggio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteGUI {

    private JFrame frame;
    private JPanel juve;

    private JTable tabellaClienti;
    private DefaultTableModel modelloTabellaClienti;

    private JButton pulsanteAggiungiCliente;
    private JButton pulsanteModificaCliente;
    private JButton pulsanteEliminaCliente;
    private JButton pulsanteMostraNoleggiCliente;
    private JButton pulsanteIndietro;

    private Controller controller;

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

    private void inizializzaPulsanti(JFrame frameChiamante) {

        pulsanteIndietro.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });

        // -------------------------
        // Aggiungi Cliente
        // -------------------------
        pulsanteAggiungiCliente.addActionListener(e -> mostraFinestraAggiungiCliente());

        // -------------------------
        // Modifica Cliente
        // -------------------------
        pulsanteModificaCliente.addActionListener(e -> {
            Cliente cliente = ottieniClienteSelezionato();
            if (cliente != null) {
                mostraFinestraModificaCliente(cliente);
            } else {
                mostraMessaggio("Seleziona un cliente prima di modificarlo.");
            }
        });

        // -------------------------
        // Elimina Cliente
        // -------------------------
        pulsanteEliminaCliente.addActionListener(e -> {
            Cliente cliente = ottieniClienteSelezionato();
            if (cliente != null) {
                controller.deleteCliente(cliente.getId());
                aggiornaTabellaClienti();
            } else {
                mostraMessaggio("Seleziona un cliente prima di eliminarlo.");
            }
        });

        // -------------------------
        // Mostra Noleggi Cliente
        // -------------------------
        pulsanteMostraNoleggiCliente.addActionListener(e -> {
            Cliente cliente = ottieniClienteSelezionato();
            if (cliente != null) {
                mostraNoleggiCliente(cliente);
            } else {
                mostraMessaggio("Seleziona un cliente prima di vedere i noleggi.");
            }
        });
    }

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

    private Cliente ottieniClienteSelezionato() {

        int rigaSelezionata = tabellaClienti.getSelectedRow();

        if (rigaSelezionata == -1) {
            return null;
        }

        int idCliente = (int) modelloTabellaClienti.getValueAt(rigaSelezionata, 0);

        return controller.getClienteById(idCliente);
    }

    private void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(frame, messaggio);
    }

    // -----------------------------------------------------
    // FINESTRA AGGIUNGI CLIENTE
    // -----------------------------------------------------
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

    // -----------------------------------------------------
    // FINESTRA MODIFICA CLIENTE
    // -----------------------------------------------------
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

    // -----------------------------------------------------
    // FINESTRA MOSTRA NOLEGGI CLIENTE
    // -----------------------------------------------------
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
