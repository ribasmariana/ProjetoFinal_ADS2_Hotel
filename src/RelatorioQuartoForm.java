import model.Quarto;
import model.Servico;
import relatorios.RelatorioQuarto;
import relatorios.RelatorioServico;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioQuartoForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Código","Tipo","Valor","Número do quarto","Disponibilidade","Limite pessoas",""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioQuarto tabela;

    public RelatorioQuartoForm(Vector<Quarto> vetorDados) {
        iniciarComponentes(vetorDados);
    }


    public void iniciarComponentes(Vector<Quarto> vetorDados) {
        tabela = new RelatorioQuarto(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioQuarto.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio(List<Quarto> quartos) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatorio");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    Main.chamaMenuPrincipal();
                }

            });
            Vector<Quarto> vetorDados = new Vector<Quarto>();
            for (Quarto quarto : quartos) {
                vetorDados.add(quarto);
            }

            frame.getContentPane().add(new RelatorioQuartoForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

