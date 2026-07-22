package gui;

import controller.Controller;
import model.Auto;
import model.Noleggio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AutoGUI {

    private JFrame frame;
    private JPanel napoli;

    private JTable tabellaAuto;
    private DefaultTableModel modelloTabellaAuto;

    private JButton pulsanteAggiungiAuto;
    private JButton pulsanteModificaAuto;
    private JButton pulsanteEliminaAuto;
    private JButton pulsanteCambiaStatoAuto;
    private JButton pulsanteMostraNoleggiAuto;
    private JButton pulsanteIndietro;

    private Controller controller;

    public AutoGUI(JFrame frameChiamante, Controller controller) {
        this.controller = controller;

        creaComponentiForm();

        frame = new JFrame("Gestione Auto");
        frame.setContentPane(napoli);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inizializzaTabellaAuto();
        inizializzaPulsanti(frameChiamante);

        aggiornaTabellaAuto();

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frameChiamante.setVisible(false);
        frame.setVisible(true);
    }

    private void creaComponentiForm() {
        napoli = new JPanel(new BorderLayout(10, 10));

        tabellaAuto = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaAuto);
        napoli.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new GridLayout(1, 6, 10, 0));

        pulsanteAggiungiAuto = new JButton("Aggiungi");
        pulsanteModificaAuto = new JButton("Modifica");
        pulsanteEliminaAuto = new JButton("Elimina");
        pulsanteCambiaStatoAuto = new JButton("Cambia Stato");
        pulsanteMostraNoleggiAuto = new JButton("Noleggi");
        pulsanteIndietro = new JButton("Indietro");

        panelButtons.add(pulsanteAggiungiAuto);
        panelButtons.add(pulsanteModificaAuto);
        panelButtons.add(pulsanteEliminaAuto);
        panelButtons.add(pulsanteCambiaStatoAuto);
        panelButtons.add(pulsanteMostraNoleggiAuto);
        panelButtons.add(pulsanteIndietro);

        napoli.add(panelButtons, BorderLayout.SOUTH);
    }

    private void inizializzaTabellaAuto() {
        modelloTabellaAuto = new DefaultTableModel(
                new Object[]{"ID", "Targa", "Marca", "Modello", "Porte", "Stato"},
                0
        );
        tabellaAuto.setModel(modelloTabellaAuto);
        tabellaAuto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaAuto.setRowHeight(25);
        tabellaAuto.getTableHeader().setReorderingAllowed(false);
    }

    private void inizializzaPulsanti(JFrame frameChiamante) {

        pulsanteIndietro.addActionListener(e -> {
            frameChiamante.setVisible(true);
            frame.dispose();
        });

        pulsanteAggiungiAuto.addActionListener(e -> mostraFinestraAggiungiAuto());

        pulsanteModificaAuto.addActionListener(e -> {
            Auto auto = ottieniAutoSelezionata();
            if (auto != null) {
                mostraFinestraModificaAuto(auto);
            } else {
                mostraMessaggio("Seleziona un'auto prima di modificarla.");
            }
        });

        pulsanteEliminaAuto.addActionListener(e -> {
            Auto auto = ottieniAutoSelezionata();
            if (auto != null) {
                controller.deleteAuto(auto.getId());
                aggiornaTabellaAuto();
            } else {
                mostraMessaggio("Seleziona un'auto prima di eliminarla.");
            }
        });

        pulsanteCambiaStatoAuto.addActionListener(e -> {
            Auto auto = ottieniAutoSelezionata();
            if (auto != null) {
                int riga = tabellaAuto.getSelectedRow();
                controller.toggleAutoState(auto.getId());
                aggiornaTabellaAuto();
                if (riga >= 0 && riga < tabellaAuto.getRowCount()) {
                    tabellaAuto.setRowSelectionInterval(riga, riga);
                }
            } else {
                mostraMessaggio("Seleziona un'auto prima di cambiare lo stato.");
            }
        });

        pulsanteMostraNoleggiAuto.addActionListener(e -> {
            Auto auto = ottieniAutoSelezionata();
            if (auto != null) {
                mostraNoleggiAuto(auto);
            } else {
                mostraMessaggio("Seleziona un'auto prima di vedere i noleggi.");
            }
        });
    }

    public void aggiornaTabellaAuto() {
        modelloTabellaAuto.setRowCount(0);

        List<Auto> elencoAuto = controller.getAllAuto();
        if (elencoAuto == null) return;

        for (Auto auto : elencoAuto) {
            modelloTabellaAuto.addRow(new Object[]{
                    auto.getId(),
                    auto.getTarga(),
                    auto.getMarca(),
                    auto.getModello(),
                    auto.getPorte(),
                    auto.getStato()
            });
        }
    }

    private Auto ottieniAutoSelezionata() {
        int riga = tabellaAuto.getSelectedRow();
        if (riga == -1) return null;

        int idAuto = (int) modelloTabellaAuto.getValueAt(riga, 0);
        return controller.getAutoById(idAuto);
    }

    private void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(frame, messaggio);
    }

    private void mostraFinestraAggiungiAuto() {

        JDialog dialog = new JDialog(frame, "Aggiungi Auto", true);
        dialog.setSize(400, 450);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));

        JTextField campoTarga = new JTextField();
        JTextField campoMarca = new JTextField();
        JTextField campoModello = new JTextField();
        JTextField campoPorte = new JTextField();
        JComboBox<String> campoStato = new JComboBox<>(new String[]{"DISPONIBILE", "NOLEGGIATO", "MANUTENZIONE"});

        JTextField campoIdCliente = new JTextField();
        JTextField campoDataInizio = new JTextField();
        JTextField campoDataFine = new JTextField();
        JTextField campoPrezzo = new JTextField();

        JLabel labelIdCliente = new JLabel("ID Cliente:");
        JLabel labelDataInizio = new JLabel("Data Inizio (yyyy-MM-dd):");
        JLabel labelDataFine = new JLabel("Data Fine (yyyy-MM-dd):");
        JLabel labelPrezzo = new JLabel("Prezzo:");

        labelIdCliente.setVisible(false);
        campoIdCliente.setVisible(false);
        labelDataInizio.setVisible(false);
        campoDataInizio.setVisible(false);
        labelDataFine.setVisible(false);
        campoDataFine.setVisible(false);
        labelPrezzo.setVisible(false);
        campoPrezzo.setVisible(false);

        campoStato.addActionListener(e -> {
            boolean noleggiato = campoStato.getSelectedItem().toString().equals("NOLEGGIATO");

            labelIdCliente.setVisible(noleggiato);
            campoIdCliente.setVisible(noleggiato);
            labelDataInizio.setVisible(noleggiato);
            campoDataInizio.setVisible(noleggiato);
            labelDataFine.setVisible(noleggiato);
            campoDataFine.setVisible(noleggiato);
            labelPrezzo.setVisible(noleggiato);
            campoPrezzo.setVisible(noleggiato);

            dialog.setSize(400, noleggiato ? 550 : 450);
        });

        panel.add(new JLabel("Targa:"));
        panel.add(campoTarga);
        panel.add(new JLabel("Marca:"));
        panel.add(campoMarca);
        panel.add(new JLabel("Modello:"));
        panel.add(campoModello);
        panel.add(new JLabel("Porte:"));
        panel.add(campoPorte);
        panel.add(new JLabel("Stato:"));
        panel.add(campoStato);

        panel.add(labelIdCliente);
        panel.add(campoIdCliente);
        panel.add(labelDataInizio);
        panel.add(campoDataInizio);
        panel.add(labelDataFine);
        panel.add(campoDataFine);
        panel.add(labelPrezzo);
        panel.add(campoPrezzo);

        JButton btnSalva = new JButton("Salva");
        panel.add(btnSalva);

        dialog.add(panel);

        btnSalva.addActionListener(ev -> {
            try {
                String targa = campoTarga.getText();
                String marca = campoMarca.getText();
                String modello = campoModello.getText();
                int porte = Integer.parseInt(campoPorte.getText());
                String stato = campoStato.getSelectedItem().toString();

                Auto nuovaAuto = new Auto(0, targa, marca, modello, porte, stato);
                controller.insertAuto(nuovaAuto);

                if (stato.equals("NOLEGGIATO")) {
                    int idCliente = Integer.parseInt(campoIdCliente.getText());
                    String dataInizio = campoDataInizio.getText();
                    String dataFine = campoDataFine.getText();
                    double prezzo = Double.parseDouble(campoPrezzo.getText());

                    Noleggio n = new Noleggio(
                            0,
                            idCliente,
                            nuovaAuto.getId(),
                            dataInizio,
                            dataFine,
                            prezzo
                    );

                    controller.insertNoleggio(n);
                }

                aggiornaTabellaAuto();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    private void mostraFinestraModificaAuto(Auto auto) {

        JDialog dialog = new JDialog(frame, "Modifica Auto", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField campoTarga = new JTextField(auto.getTarga());
        JTextField campoMarca = new JTextField(auto.getMarca());
        JTextField campoModello = new JTextField(auto.getModello());
        JTextField campoPorte = new JTextField(String.valueOf(auto.getPorte()));
        JComboBox<String> campoStato = new JComboBox<>(new String[]{"DISPONIBILE", "NOLEGGIATO", "MANUTENZIONE"});
        campoStato.setSelectedItem(auto.getStato());

        panel.add(new JLabel("Targa:"));
        panel.add(campoTarga);
        panel.add(new JLabel("Marca:"));
        panel.add(campoMarca);
        panel.add(new JLabel("Modello:"));
        panel.add(campoModello);
        panel.add(new JLabel("Porte:"));
        panel.add(campoPorte);
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
                int porte = Integer.parseInt(campoPorte.getText());
                String stato = campoStato.getSelectedItem().toString();

                Auto autoAggiornata = new Auto(auto.getId(), targa, marca, modello, porte, stato);
                controller.updateAuto(autoAggiornata);

                aggiornaTabellaAuto();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    private void mostraNoleggiAuto(Auto auto) {

        JDialog dialog = new JDialog(frame, "Noleggi dell'Auto", true);
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
            if (n.getIdVeicolo() == auto.getId()) {
                modello.addRow(new Object[]{
                        n.getId(),
                        n.getIdCliente(),
                        n.getDataInizioString(),
                        n.getDataFineString(),
                        n.getCosto()
                });
            }
        }

        dialog.add(new JScrollPane(tabella));
        dialog.setVisible(true);
    }
}
