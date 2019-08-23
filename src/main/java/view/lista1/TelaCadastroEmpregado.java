package view.lista1;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Empregado;
import model.entity.lista1.EmpregadoOperacional;
import model.entity.lista1.Gerente;

import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import controller.lista1.EmpregadoController;

import java.awt.Font;

public class TelaCadastroEmpregado {

	private JFrame frmCadastroDeEmpregado;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSalarioBruto;
	private JTextField txtComissao;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblIdade;
	private JLabel lblSexo;
	private JLabel lblTipo;
	private JLabel lblSalarioBruto;
	private JLabel lblComissao;
	private JSpinner spnIdade;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JComboBox cbTipo;
	private Empregado novoEmpregado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEmpregado window = new TelaCadastroEmpregado();
					window.frmCadastroDeEmpregado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroEmpregado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeEmpregado = new JFrame();
		frmCadastroDeEmpregado.setTitle("Cadastro de empregado");
		frmCadastroDeEmpregado.setBounds(100, 100, 248, 297);
		frmCadastroDeEmpregado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeEmpregado.getContentPane().setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(16, 32, 90, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblNome);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(16, 57, 90, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblCpf);
		
		lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(16, 82, 90, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblIdade);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(16, 107, 90, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblSexo);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(16, 132, 90, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblTipo);
		
		lblSalarioBruto = new JLabel("Salário Bruto (R$):");
		lblSalarioBruto.setBounds(16, 157, 90, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblSalarioBruto);
		
		lblComissao = new JLabel("Comissão (R$):");
		lblComissao.setBounds(16, 182, 90, 14);
		lblComissao.setVisible(false);
		frmCadastroDeEmpregado.getContentPane().add(lblComissao);
		
		txtNome = new JTextField();
		txtNome.setBounds(116, 32, 101, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(116, 57, 101, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		
		spnIdade = new JSpinner();
		spnIdade.setBounds(116, 82, 101, 20);
		frmCadastroDeEmpregado.getContentPane().add(spnIdade);
		
		ButtonGroup group = new ButtonGroup();
		rbMasculino = new JRadioButton("M");
		rbMasculino.setBounds(128, 106, 41, 23);
		frmCadastroDeEmpregado.getContentPane().add(rbMasculino);
		
		rbFeminino = new JRadioButton("F");
		rbFeminino.setBounds(171, 106, 46, 23);
		frmCadastroDeEmpregado.getContentPane().add(rbFeminino);

		group.add(rbFeminino);
		group.add(rbMasculino);
		
		String[] tipos = {"Selecione", EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR, 
				EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE, 
				EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL};
		cbTipo = new JComboBox(tipos);
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tipoSelecionado = (String) cbTipo.getSelectedItem();
				if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR)) {
					txtComissao.setVisible(true);
					lblComissao.setVisible(true);
				}else if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE)) {
					txtComissao.setVisible(true);
					lblComissao.setVisible(true);
				} else if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL)) {
					txtComissao.setVisible(false);
					lblComissao.setVisible(false);
					txtComissao.setText("");
				} else {//"Selecione"
					txtComissao.setVisible(false);
					txtComissao.setText("");
					lblComissao.setVisible(false);
				}
			}
		});
		cbTipo.setBounds(116, 132, 101, 20);
		frmCadastroDeEmpregado.getContentPane().add(cbTipo);
		
		txtSalarioBruto = new JTextField();
		txtSalarioBruto.setBounds(116, 157, 101, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtSalarioBruto);
		txtSalarioBruto.setColumns(10);
		
		txtComissao = new JTextField();
		txtComissao.setVisible(false);
		txtComissao.setBounds(116, 182, 101, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtComissao);
		txtComissao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char sexoSelecionado = ' ';
				
				if(rbMasculino.isSelected()) {
					sexoSelecionado = 'M';
				}else if(rbFeminino.isSelected()) {
					sexoSelecionado = 'F';
				}
				
				int idadeInteira = (Integer) spnIdade.getValue();
				
				EmpregadoController controladora = new EmpregadoController();
				
				String mensagem = controladora.salvar(txtNome.getText(),txtCPF.getText(),sexoSelecionado,
						idadeInteira, txtSalarioBruto.getText(),
						(String) cbTipo.getSelectedItem(), txtComissao.getText()
						);
				
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnSalvar.setBounds(16, 207, 201, 40);
		frmCadastroDeEmpregado.getContentPane().add(btnSalvar);
		
		JLabel lblNovoEmpregado = new JLabel("Novo empregado");
		lblNovoEmpregado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNovoEmpregado.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoEmpregado.setBounds(16, 0, 201, 32);
		frmCadastroDeEmpregado.getContentPane().add(lblNovoEmpregado);
	}
}