package view.painel.exemplos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import view.exemplos.TelaPrincipalComMenu;

public class PanelListagemClientes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1969373599090553007L;
	private JTextField txtNome;
	private JTable tblResultados;
	private JButton btnChamarPai;

	/**
	 * Create the panel.
	 */
	public PanelListagemClientes() {
		setLayout(new MigLayout("", "[305px][15px][130px][][]", "[37.00px][250px,grow]"));

		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "cell 0 0,alignx left,growy");

		txtNome = new JTextField();
		add(txtNome, "cell 0 0,grow");
		txtNome.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		add(btnFiltrar, "cell 2 0,grow");

		tblResultados = new JTable();
		tblResultados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = tblResultados.getSelectedRow();

			}
		});
		tblResultados
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "#", "New column", "New column" }));
		add(tblResultados, "cell 0 1 3 1,grow");

		// Exemplo de listener para chamar método do pai dessa tela (o Menu)
		// A outra solução é colocar o listener na classe do menu (TelaPrincipalComMenu)
		final TelaPrincipalComMenu paiDoPainel = (TelaPrincipalComMenu) SwingUtilities.windowForComponent(this);
		btnChamarPai = new JButton("Chamar PAI");
		btnChamarPai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				paiDoPainel.chamarPai();
			}
		});
		add(btnChamarPai, "cell 4 1");
	}
}