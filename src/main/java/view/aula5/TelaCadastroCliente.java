package view.aula5;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.privatejgoodies.forms.layout.ColumnSpec;
import com.privatejgoodies.forms.layout.FormLayout;
import com.privatejgoodies.forms.layout.FormSpecs;
import com.privatejgoodies.forms.layout.RowSpec;

import controller.aula05.ClienteController;
import controller.aula05.EnderecoController;
import controller.aula05.TelefoneController;
import model.entity.aula05.Cliente;
import model.entity.aula05.Endereco;
import model.entity.aula05.Telefone;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JFormattedTextField txtCPF;
	private JTextField txtCodigoPais;
	private JTextField txtDdd;
	private JTextField txtNumero;
	private JTable tblTelefones;
	private JLabel lblTelefonesCadastrados;
	private String[] colunasTabelaTelefones = { "País", "DDD", "Número", "Tipo" };
	private JComboBox cbEndereco;
	private JButton btnSalvar;
	private JComboBox cbTipo;
	private ArrayList<Telefone> telefonesDoNovoCliente;
	private ArrayList<Endereco> todosOsEnderecos;
	private String[] tipos = { TelefoneController.TIPO_TELEFONE_FIXO, TelefoneController.TIPO_TELEFONE_MOVEL };
	private Cliente novoCliente = null;
	private JButton btnAdicionarTelefone;
	private JLabel lblNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroCliente() {
		setTitle("Cadastro de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("70px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("3px"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("21px"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("40px"),
						ColumnSpec.decode("5px"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("20px"),
						FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("69px"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("98px"), ColumnSpec.decode("130px"), },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("20px"), FormSpecs.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("23px"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("2px"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"), FormSpecs.UNRELATED_GAP_ROWSPEC,
						RowSpec.decode("20px"), FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("20px"),
						FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("2px"), FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("20px"), FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("200px"), }));

		lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "2, 2, fill, fill");

		txtNome = new JTextField();
		contentPane.add(txtNome, "6, 2, 4, 1, fill, top");
		txtNome.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		contentPane.add(lblSobrenome, "11, 2, 3, 1, fill, fill");

		txtSobrenome = new JTextField();
		contentPane.add(txtSobrenome, "15, 2, left, top");
		txtSobrenome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		contentPane.add(lblCpf, "16, 2, left, fill");

		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCPF = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
		}
		contentPane.add(txtCPF, "16, 2, right, top");
		txtCPF.setColumns(10);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		contentPane.add(lblEndereco, "2, 4, 3, 1, fill, center");

		JSeparator separator = new JSeparator();
		contentPane.add(separator, "2, 8, 15, 1, fill, top");

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteController clienteController = new ClienteController();
				String nomeDigitado = txtNome.getText();
				String sobrenomeDigitado = txtSobrenome.getText();
				String cpfDigitado = txtCPF.getText().replace("-", "").replace(".", "");
				Endereco enderecoSelecionado = (Endereco) cbEndereco.getSelectedItem();

				String mensagem = clienteController.validarCamposSalvar(nomeDigitado, sobrenomeDigitado, cpfDigitado,
						enderecoSelecionado);

				if (mensagem.isEmpty()) {
					novoCliente = new Cliente(nomeDigitado, sobrenomeDigitado, cpfDigitado, new ArrayList<Telefone>(),
							enderecoSelecionado);
					novoCliente = clienteController.salvar(novoCliente);

					if (novoCliente.getId() > 0) {
						btnAdicionarTelefone.setEnabled(true);
						btnSalvar.setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(null, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		contentPane.add(btnSalvar, "13, 6, 3, 1, center, top");

		JLabel lblNovoTelefone = new JLabel("Novo telefone");
		lblNovoTelefone.setForeground(Color.WHITE);
		lblNovoTelefone.setBackground(Color.DARK_GRAY);
		lblNovoTelefone.setOpaque(true);
		lblNovoTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNovoTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNovoTelefone, "2, 10, 15, 1, fill, fill");

		JLabel lblCodigoPais = new JLabel("Cód. país:");
		contentPane.add(lblCodigoPais, "2, 12, fill, fill");

		txtCodigoPais = new JTextField();
		contentPane.add(txtCodigoPais, "4, 12, 3, 1, fill, top");
		txtCodigoPais.setColumns(10);

		JLabel lblDdd = new JLabel("DDD:");
		contentPane.add(lblDdd, "8, 12, fill, fill");

		txtDdd = new JTextField();
		contentPane.add(txtDdd, "9, 12, 3, 1, fill, top");
		txtDdd.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero:");
		contentPane.add(lblNumero, "13, 12, fill, fill");

		txtNumero = new JTextField();
		contentPane.add(txtNumero, "15, 12, left, top");
		txtNumero.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		contentPane.add(lblTipo, "16, 12, left, fill");

		cbTipo = new JComboBox(tipos);
		cbTipo.setSelectedIndex(-1);
		contentPane.add(cbTipo, "16, 12, fill, top");

		btnAdicionarTelefone = new JButton("Adicionar telefone");
		btnAdicionarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelefoneController controller = new TelefoneController();

				String codigoPaisInformado = txtCodigoPais.getText();
				String dddInformado = txtDdd.getText();
				String numeroInformado = txtNumero.getText();
				String tipoLinhaInformado = (String) cbTipo.getSelectedItem();

				String mensagemValidacao = controller.validarNovoTelefone(codigoPaisInformado, dddInformado,
						numeroInformado, tipoLinhaInformado);

				if (mensagemValidacao.isEmpty()) {
					Telefone novoTelefone = new Telefone(novoCliente, codigoPaisInformado, dddInformado,
							numeroInformado, tipoLinhaInformado, true);
					mensagemValidacao = controller.salvar(novoTelefone);

					if (mensagemValidacao.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Telefone " + "adicionado", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						atualizarTabelaTelefones();
					} else {
						JOptionPane.showMessageDialog(null, mensagemValidacao, "Atenção", JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, mensagemValidacao, "Atenção", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdicionarTelefone.setEnabled(false);
		contentPane.add(btnAdicionarTelefone, "11, 14, 5, 1, fill, fill");

		JSeparator separator_1 = new JSeparator();
		contentPane.add(separator_1, "2, 16, 15, 1, fill, top");

		tblTelefones = new JTable();
		contentPane.add(tblTelefones, "2, 20, 15, 1, fill, fill");

		limparTabela();

		lblTelefonesCadastrados = new JLabel("Telefones do cliente");
		lblTelefonesCadastrados.setForeground(Color.WHITE);
		lblTelefonesCadastrados.setBackground(Color.DARK_GRAY);

		// Para que a cor de fundo do label apare�a
		lblTelefonesCadastrados.setOpaque(true);

		lblTelefonesCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonesCadastrados.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblTelefonesCadastrados, "2, 18, 15, 1, fill, fill");

		// C�digo do construtor da tela
		consultarEnderecos();
		cbEndereco = new JComboBox(todosOsEnderecos.toArray());
		cbEndereco.setSelectedIndex(-1);
		contentPane.add(cbEndereco, "6, 4, 11, 1, fill, top");
	}

	private void consultarEnderecos() {
		EnderecoController controller = new EnderecoController();
		todosOsEnderecos = controller.consultarTodos();
	}

	protected void atualizarTabelaTelefones() {
		TelefoneController controller = new TelefoneController();

		if (novoCliente != null && novoCliente.getId() > 0) {
			telefonesDoNovoCliente = controller.consultarTelefonesDoCliente(novoCliente.getId());
		}

		limparTabela();

		DefaultTableModel model = (DefaultTableModel) tblTelefones.getModel();
		for (Telefone telefone : telefonesDoNovoCliente) {
			String[] novaLinha = new String[5];
			novaLinha[0] = telefone.getCodigoPais();
			novaLinha[1] = telefone.getDdd();
			novaLinha[2] = telefone.getNumero();
			novaLinha[3] = telefone.getTipoLinha();
			model.addRow(novaLinha);
		}
	}

	private void limparTabela() {
		tblTelefones
				.setModel(new DefaultTableModel(new Object[][] { colunasTabelaTelefones, }, colunasTabelaTelefones));
	}
}
