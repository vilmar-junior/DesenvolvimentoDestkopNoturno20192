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
		frmCadastroDeEmpregado.setBounds(100, 100, 224, 297);
		frmCadastroDeEmpregado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeEmpregado.getContentPane().setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(28, 32, 70, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblNome);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(28, 57, 70, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblCpf);
		
		lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(28, 82, 70, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblIdade);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(28, 107, 70, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblSexo);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(28, 132, 70, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblTipo);
		
		lblSalarioBruto = new JLabel("Salário Bruto:");
		lblSalarioBruto.setBounds(28, 157, 70, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblSalarioBruto);
		
		lblComissao = new JLabel("Comissão:");
		lblComissao.setBounds(28, 182, 70, 14);
		lblComissao.setVisible(false);
		frmCadastroDeEmpregado.getContentPane().add(lblComissao);
		
		txtNome = new JTextField();
		txtNome.setBounds(101, 29, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(101, 54, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		
		spnIdade = new JSpinner();
		spnIdade.setBounds(101, 79, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(spnIdade);
		
		ButtonGroup group = new ButtonGroup();
		rbMasculino = new JRadioButton("M");
		rbMasculino.setBounds(98, 103, 41, 23);
		frmCadastroDeEmpregado.getContentPane().add(rbMasculino);
		
		rbFeminino = new JRadioButton("F");
		rbFeminino.setBounds(141, 103, 46, 23);
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
					novoEmpregado = new Diretor();
					txtComissao.setVisible(true);
					lblComissao.setVisible(true);
				}else if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE)) {
					novoEmpregado = new Gerente();
					txtComissao.setVisible(true);
					lblComissao.setVisible(true);
				} else if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL)) {
					novoEmpregado = new EmpregadoOperacional();
					txtComissao.setVisible(false);
					lblComissao.setVisible(false);
					txtComissao.setText("");
				} else {//"Selecione"
					novoEmpregado = null;
					txtComissao.setVisible(false);
					txtComissao.setText("");
					lblComissao.setVisible(false);
				}
			}
		});
		cbTipo.setBounds(101, 129, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(cbTipo);
		
		txtSalarioBruto = new JTextField();
		txtSalarioBruto.setBounds(101, 154, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtSalarioBruto);
		txtSalarioBruto.setColumns(10);
		
		txtComissao = new JTextField();
		txtComissao.setVisible(false);
		txtComissao.setBounds(101, 179, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtComissao);
		txtComissao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(novoEmpregado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um tipo!", "Atenção!", JOptionPane.ERROR_MESSAGE);
				}else {
					novoEmpregado.setNome(txtNome.getText());
					novoEmpregado.setCpf(txtCPF.getText());
					novoEmpregado.setIdade((Integer) spnIdade.getValue());
					
					if(rbMasculino.isSelected()) {
						novoEmpregado.setSexo('M');
					}
					
					//TODO continuar na sexta...
				}
				
				
				//TODO é apenas um exemplo inicial
				//JAMAIS CHAMEM DAOs NA TELA!!!
				
				
			}
		});
		btnSalvar.setBounds(28, 207, 159, 40);
		frmCadastroDeEmpregado.getContentPane().add(btnSalvar);
	}
}