package gui;

import controller.Controller;
import model.Scooter;
import model.Noleggio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ScooterGUI {

    private JFrame frame;
    private JPanel pannelo1;

    private JTable tabellaScooter;
    private DefaultTableModel modelloTabellaScooter;

    private JButton pulsanteAggiungiScooter;
    private JButton pulsanteModificaScooter;
    private JButton pulsanteEliminaScooter;
    private JButton pulsanteCambiaStatoScooter;
    private JButton pulsanteMostraNoleggiScooter;
    private JButton pulsanteIndietro;

    private Controller controller;

    public ScooterGUI(JFrame frameChiamante, Controller controller) {

        this.controller = controller;

        creaComponentiForm();

        frame = new JFrame("Gestione Scooter");
        frame.setContentPane(pannelo1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inizializzaTabellaScooter();
        inizializzaPulsanti(frameChiamante);

        aggiornaTabellaScooter();

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
    }

    private void creaComponentiForm() {

        pannelo1 = new JPanel(new BorderLayout(10, 10));

        tabellaScooter = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaScooter);
        pannelo1.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new GridLayout(1, 6, 10, 0));

        pulsanteAggiungiScooter = new JButton("Aggiungi");
        pulsanteModificaScooter = new JButton("Modifica");
        pulsanteEliminaScooter = new JButton("Elimina");
        pulsanteCambiaStatoScooter = new JButton("Cambia Stato");
        pulsanteMostraNoleggiScooter = new JButton("Noleggi");
        pulsanteIndietro = new JButton("Indietro");

        panelButtons.add(pulsanteAggiungiScooter);
        panelButtons.add(pulsanteModificaScooter);
        panelButtons.add(pulsanteEliminaScooter);
        panelButtons.add(pulsanteCambiaStatoScooter);
        panelButtons.add(pulsanteMostraNoleggiScooter);
        panelButtons.add(pulsanteIndietro);

        pannelo1.add(panelButtons, BorderLayout.SOUTH);
    }

    private void inizializzaTabellaScooter() {

        modelloTabellaScooter = new DefaultTableModel(
                new Object[]{"ID", "Targa", "Marca", "Modello", "Cilindrata", "Stato"},
                0
        );

        tabellaScooter.setModel(modelloTabellaScooter);
        tabellaScooter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaScooter.setRowHeight(25);
        tabellaScooter.getTableHeader().setReorderingAllowed(false);
    }

    private void inizializzaPulsanti(JFrame frameChiamante) {

        pulsanteIndietro.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });

        // -------------------------
        // Aggiungi Scooter
        // -------------------------
        pulsanteAggiungiScooter.addActionListener(e -> mostraFinestraAggiungiScooter());

        // -------------------------
        // Modifica Scooter
        // -------------------------
        pulsanteModificaScooter.addActionListener(e -> {
            Scooter scooter = ottieniScooterSelezionato();
            if (scooter != null) {
                mostraFinestraModificaScooter(scooter);
            } else {
                mostraMessaggio("Seleziona uno scooter prima di modificarlo.");
            }
        });

        // -------------------------
        // Elimina Scooter
        // -------------------------
        pulsanteEliminaScooter.addActionListener(e -> {
            Scooter scooter = ottieniScooterSelezionato();
            if (scooter != null) {
                controller.deleteScooter(scooter.getId());
                aggiornaTabellaScooter();
            } else {
                mostraMessaggio("Seleziona uno scooter prima di eliminarlo.");
            }
        });

        // -------------------------
        // Cambia Stato Scooter
        // -------------------------
        pulsanteCambiaStatoScooter.addActionListener(e -> {
            Scooter scooter = ottieniScooterSelezionato();
            if (scooter != null) {
                controller.toggleScooterState(scooter.getId());
                aggiornaTabellaScooter();
            } else {
                mostraMessaggio("Seleziona uno scooter prima di cambiare lo stato.");
            }
        });

        // -------------------------
        // Mostra Noleggi Scooter
        // -------------------------
        pulsanteMostraNoleggiScooter.addActionListener(e -> {
            Scooter scooter = ottieniScooterSelezionato();
            if (scooter != null) {
                mostraNoleggiScooter(scooter);
            } else {
                mostraMessaggio("Seleziona uno scooter prima di vedere i noleggi.");
            }
        });
    }

    public void aggiornaTabellaScooter() {

        modelloTabellaScooter.setRowCount(0);

        List<Scooter> elencoScooter = controller.getAllScooter();
        if (elencoScooter == null) return;

        for (Scooter scooter : elencoScooter) {
            modelloTabellaScooter.addRow(new Object[]{
                    scooter.getId(),
                    scooter.getTarga(),
                    scooter.getMarca(),
                    scooter.getModello(),
                    scooter.getCilindrata(),
                    scooter.getStato()
            });
        }
    }

    private Scooter ottieniScooterSelezionato() {

        int rigaSelezionata = tabellaScooter.getSelectedRow();

        if (rigaSelezionata == -1) {
            return null;
        }

        int idScooter = (int) modelloTabellaScooter.getValueAt(rigaSelezionata, 0);

        return controller.getScooterById(idScooter);
    }

    private void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(frame, messaggio);
    }

    // -----------------------------------------------------
    // FINESTRA AGGIUNGI SCOOTER
    // -----------------------------------------------------
    private void mostraFinestraAggiungiScooter() {

        JDialog dialog = new JDialog(frame, "Aggiungi Scooter", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoTarga = new JTextField();
        JTextField campoMarca = new JTextField();
        JTextField campoModello = new JTextField();
        JTextField campoCilindrata = new JTextField();
        JComboBox<String> campoStato = new JComboBox<>(new String[]{"Disponibile", "Non disponibile"});

        panel.add(new JLabel("Targa:"));
        panel.add(campoTarga);
        panel.add(new JLabel("Marca:"));
        panel.add(campoMarca);
        panel.add(new JLabel("Modello:"));
        panel.add(campoModello);
        panel.add(new JLabel("Cilindrata:"));
        panel.add(campoCilindrata);
        panel.add(new JLabel("Stato:"));
        panel.add(campoStato);

        JButton btnSalva = new JButton("Salva");
        panel.add(btnSalva);

        dialog.add(panel);

        btnSalva.addActionListener(ev -> {
            try {
                String targa = campoTarga.getText();
                String marca = campoMarca.getText();
                String modello = campoModello.getText();
                int cilindrata = Integer.parseInt(campoCilindrata.getText());
                String stato = campoStato.getSelectedItem().toString();

                Scooter nuovoScooter = new Scooter(0, targa, marca, modello, cilindrata, stato);
                controller.insertScooter(nuovoScooter);

                aggiornaTabellaScooter();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    // -----------------------------------------------------
    // FINESTRA MODIFICA SCOOTER
    // -----------------------------------------------------
    private void mostraFinestraModificaScooter(Scooter scooter) {

        JDialog dialog = new JDialog(frame, "Modifica Scooter", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoTarga = new JTextField(scooter.getTarga());
        JTextField campoMarca = new JTextField(scooter.getMarca());
        JTextField campoModello = new JTextField(scooter.getModello());
        JTextField campoCilindrata = new JTextField(String.valueOf(scooter.getCilindrata()));
        JComboBox<String> campoStato = new JComboBox<>(new String[]{"Disponibile", "Non disponibile"});
        campoStato.setSelectedItem(scooter.getStato());

        panel.add(new JLabel("Targa:"));
        panel.add(campoTarga);
        panel.add(new JLabel("Marca:"));
        panel.add(campoMarca);
        panel.add(new JLabel("Modello:"));
        panel.add(campoModello);
        panel.add(new JLabel("Cilindrata:"));
        panel.add(campoCilindrata);
        panel.add(new JLabel("Stato:"));
        panel.add(campoStato);

        JButton btnAggiorna = new JButton("Aggiorna");
        panel.add(btnAggiorna);

        dialog.add(panel);

        btnAggiorna.addActionListener(ev -> {
            try {
                String targa = campoTarga.getText();
                String marca = campoMarca.getText();
                String modello = campoModello.getText();
                int cilindrata = Integer.parseInt(campoCilindrata.getText());
                String stato = campoStato.getSelectedItem().toString();

                Scooter scooterAggiornato =
                        new Scooter(scooter.getId(), targa, marca, modello, cilindrata, stato);

                controller.updateScooter(scooterAggiornato);

                aggiornaTabellaScooter();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    // -----------------------------------------------------
    // MOSTRA NOLEGGI SCOOTER
    // -----------------------------------------------------
    private void mostraNoleggiScooter(Scooter scooter) {

        JDialog dialog = new JDialog(frame, "Noleggi dello Scooter", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(frame);

        JTable tabella = new JTable();
        DefaultTableModel modello = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Inizio", "Fine", "Prezzo"},
                0
        );

        tabella.setModel(modello);
        tabella.setRowHeight(25);

        List<Noleggio> lista = controller.getAllNoleggi();

        for (Noleggio n : lista) {
            if (n.getIdVeicolo() == scooter.getId()) {
                modello.addRow(new Object[]{
                        n.getId(),
                        n.getIdCliente(),
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
