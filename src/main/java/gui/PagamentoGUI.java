package gui;

import controller.Controller;
import model.Pagamento;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Interfaccia grafica per la gestione dei pagamenti.
 * Permette di visualizzare, aggiungere, modificare, eliminare
 * e consultare il cliente associato a un pagamento.
 *
 * <p>La finestra mostra una tabella con tutti i pagamenti e vari pulsanti
 * per eseguire le operazioni principali.</p>
 *
 * <p>Questa classe comunica con il {@link Controller} per ottenere
 * i dati e aggiornare il database.</p>
 *
 * @see model.Pagamento
 * @see model.Cliente
 * @see controller.Controller
 *
 * @author Francesco & Vincenzo
 */
public class PagamentoGUI {

    /** Finestra principale della GUI. */
    private JFrame frame;

    /** Pannello principale che contiene tabella e pulsanti. */
    private JPanel pannello2;

    /** Tabella che mostra l'elenco dei pagamenti. */
    private JTable tabellaPagamenti;

    /** Modello della tabella che gestisce i dati dei pagamenti. */
    private DefaultTableModel modelloTabellaPagamenti;

    /** Pulsante per aggiungere un nuovo pagamento. */
    private JButton pulsanteAggiungiPagamento;

    /** Pulsante per modificare un pagamento esistente. */
    private JButton pulsanteModificaPagamento;

    /** Pulsante per eliminare un pagamento. */
    private JButton pulsanteEliminaPagamento;

    /** Pulsante per visualizzare il cliente associato al pagamento. */
    private JButton pulsanteMostraClientePagamento;

    /** Pulsante per tornare alla finestra precedente. */
    private JButton pulsanteIndietro;

    /** Controller che gestisce la logica dell'applicazione. */
    private Controller controller;

    /**
     * Costruttore della schermata Pagamento.
     *
     * @param frameChiamante finestra precedente
     * @param controller controller che gestisce le operazioni
     */
    public PagamentoGUI(JFrame frameChiamante, Controller controller) {

        this.controller = controller;

        creaComponentiForm();

        frame = new JFrame("Gestione Pagamenti");
        frame.setContentPane(pannello2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inizializzaTabellaPagamenti();
        inizializzaPulsanti(frameChiamante);

        aggiornaTabellaPagamenti();

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

        pannello2 = new JPanel(new BorderLayout(10, 10));

        tabellaPagamenti = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaPagamenti);
        pannello2.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new GridLayout(1, 5, 10, 0));

        pulsanteAggiungiPagamento = new JButton("Aggiungi");
        pulsanteModificaPagamento = new JButton("Modifica");
        pulsanteEliminaPagamento = new JButton("Elimina");
        pulsanteMostraClientePagamento = new JButton("Cliente");
        pulsanteIndietro = new JButton("Indietro");

        panelButtons.add(pulsanteAggiungiPagamento);
        panelButtons.add(pulsanteModificaPagamento);
        panelButtons.add(pulsanteEliminaPagamento);
        panelButtons.add(pulsanteMostraClientePagamento);
        panelButtons.add(pulsanteIndietro);

        pannello2.add(panelButtons, BorderLayout.SOUTH);
    }

    /**
     * Inizializza la tabella dei pagamenti impostando colonne,
     * altezza righe e modalità di selezione.
     */
    private void inizializzaTabellaPagamenti() {

        modelloTabellaPagamenti = new DefaultTableModel(
                new Object[]{"ID", "Importo", "Data", "Metodo"},
                0
        );

        tabellaPagamenti.setModel(modelloTabellaPagamenti);
        tabellaPagamenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabellaPagamenti.setRowHeight(25);
        tabellaPagamenti.getTableHeader().setReorderingAllowed(false);
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

        pulsanteAggiungiPagamento.addActionListener(e -> mostraFinestraAggiungiPagamento());

        pulsanteModificaPagamento.addActionListener(e -> {
            Pagamento pagamento = ottieniPagamentoSelezionato();
            if (pagamento != null) {
                mostraFinestraModificaPagamento(pagamento);
            } else {
                mostraMessaggio("Seleziona un pagamento prima di modificarlo.");
            }
        });

        pulsanteEliminaPagamento.addActionListener(e -> {
            Pagamento pagamento = ottieniPagamentoSelezionato();
            if (pagamento != null) {
                controller.deletePagamento(pagamento.getId());
                aggiornaTabellaPagamenti();
            } else {
                mostraMessaggio("Seleziona un pagamento prima di eliminarlo.");
            }
        });

        pulsanteMostraClientePagamento.addActionListener(e -> {
            Pagamento pagamento = ottieniPagamentoSelezionato();
            if (pagamento != null) {
                mostraClientePagamento(pagamento);
            } else {
                mostraMessaggio("Seleziona un pagamento prima di vedere il cliente.");
            }
        });
    }

    /**
     * Aggiorna la tabella dei pagamenti leggendo i dati dal controller.
     */
    public void aggiornaTabellaPagamenti() {

        modelloTabellaPagamenti.setRowCount(0);

        List<Pagamento> elencoPagamenti = controller.getAllPagamenti();
        if (elencoPagamenti == null) return;

        for (Pagamento p : elencoPagamenti) {
            modelloTabellaPagamenti.addRow(new Object[]{
                    p.getId(),
                    p.getImporto(),
                    p.getDataPagamento(),
                    p.getMetodo()
            });
        }
    }

    /**
     * Restituisce il pagamento selezionato nella tabella.
     *
     * @return pagamento selezionato oppure null se nessuna riga è selezionata
     */
    private Pagamento ottieniPagamentoSelezionato() {

        int rigaSelezionata = tabellaPagamenti.getSelectedRow();

        if (rigaSelezionata == -1) {
            return null;
        }

        int idPagamento = (int) modelloTabellaPagamenti.getValueAt(rigaSelezionata, 0);

        return controller.getPagamentoById(idPagamento);
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
     * Mostra la finestra per aggiungere un nuovo pagamento.
     */
    private void mostraFinestraAggiungiPagamento() {

        JDialog dialog = new JDialog(frame, "Aggiungi Pagamento", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField campoImporto = new JTextField();
        JTextField campoData = new JTextField();
        JTextField campoMetodo = new JTextField();
        JTextField campoIdCliente = new JTextField();

        panel.add(new JLabel("Importo:"));
        panel.add(campoImporto);
        panel.add(new JLabel("Data:"));
        panel.add(campoData);
        panel.add(new JLabel("Metodo:"));
        panel.add(campoMetodo);
        panel.add(new JLabel("ID Cliente:"));
        panel.add(campoIdCliente);

        JButton btnSalva = new JButton("Salva");
        panel.add(btnSalva);

        dialog.add(panel);

        btnSalva.addActionListener(ev -> {
            try {
                double importo = Double.parseDouble(campoImporto.getText());
                String data = campoData.getText();
                String metodo = campoMetodo.getText();
                int idCliente = Integer.parseInt(campoIdCliente.getText());

                Pagamento nuovoPagamento = new Pagamento(0, importo, data, metodo, idCliente);
                controller.insertPagamento(nuovoPagamento);

                aggiornaTabellaPagamenti();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * Mostra la finestra per modificare un pagamento esistente.
     *
     * @param pagamento pagamento da modificare
     */
    private void mostraFinestraModificaPagamento(Pagamento pagamento) {

        JDialog dialog = new JDialog(frame, "Modifica Pagamento", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(frame);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField campoImporto = new JTextField(String.valueOf(pagamento.getImporto()));
        JTextField campoData = new JTextField(pagamento.getDataPagamento().toString());
        JTextField campoMetodo = new JTextField(pagamento.getMetodo());
        JTextField campoIdCliente = new JTextField(String.valueOf(pagamento.getIdCliente()));

        panel.add(new JLabel("Importo:"));
        panel.add(campoImporto);
        panel.add(new JLabel("Data:"));
        panel.add(campoData);
        panel.add(new JLabel("Metodo:"));
        panel.add(campoMetodo);
        panel.add(new JLabel("ID Cliente:"));
        panel.add(campoIdCliente);

        JButton btnAggiorna = new JButton("Aggiorna");
        panel.add(btnAggiorna);

        dialog.add(panel);

        btnAggiorna.addActionListener(ev -> {
            try {
                double importo = Double.parseDouble(campoImporto.getText());
                String data = campoData.getText();
                String metodo = campoMetodo.getText();
                int idCliente = Integer.parseInt(campoIdCliente.getText());

                Pagamento pagamentoAggiornato =
                        new Pagamento(pagamento.getId(), importo, data, metodo, idCliente);

                controller.updatePagamento(pagamentoAggiornato);

                aggiornaTabellaPagamenti();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Errore nei dati inseriti.");
            }
        });

        dialog.setVisible(true);
    }

    /**
     * Mostra i dettagli del cliente associato al pagamento.
     *
     * @param pagamento pagamento selezionato
     */
    private void mostraClientePagamento(Pagamento pagamento) {

        Cliente c = controller.getClienteById(pagamento.getIdCliente());

        JOptionPane.showMessageDialog(frame,
                "ID Cliente: " + c.getId() +
                        "\nNome: " + c.getNome() +
                        "\nCognome: " + c.getCognome() +
                        "\nTelefono: " + c.getTelefono() +
                        "\nEmail: " + c.getEmail(),
                "Dettagli Cliente",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
