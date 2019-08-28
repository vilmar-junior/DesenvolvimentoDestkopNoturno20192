package view.lista1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.lista1.EmpregadoController;
import model.entity.lista1.Empregado;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaConsultaEmpregados extends JFrame {

	private JPanel contentPane;
	private JTable tblEmpregados;
	private String[] nomesColunas = {"Nome", "CPF", "Sexo", "Idade", "Salário"};
	private ArrayList<Empregado> empregados;
	private JButton btnExcluir;
	private JButton btnEditar;
	private EmpregadoController controller = new EmpregadoController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaEmpregados frame = new TelaConsultaEmpregados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaConsultaEmpregados() {
		setTitle("Consulta de empregados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTabelaEmpregados();
			}
		});
		
		tblEmpregados = new JTable();
		tblEmpregados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int indiceSelecionadoNaTabela = tblEmpregados.getSelectedRow();
				
				if(indiceSelecionadoNaTabela > 0) {
					btnExcluir.setEnabled(true);
					btnEditar.setEnabled(true);
				}else {
					btnExcluir.setEnabled(false);
					btnEditar.setEnabled(false);
				}
				
			}
		});
		limparTabelaEmpregados();
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indiceSelecionadoNaTabela = tblEmpregados.getSelectedRow();
 				Empregado empregadoSelecionado = empregados.get(indiceSelecionadoNaTabela - 1);
				
				//chamar o controller, passando o tal empregado
				String mensagem = controller.excluir(empregadoSelecionado);
				
				//pegar a mensagem retornada e mostrar na tela
				JOptionPane.showMessageDialog(null, mensagem);
				
				//atualizar a tabela, consultando novamente todos os empregados
				atualizarTabelaEmpregados();
				
			}
		});
		
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(225)
							.addComponent(btnBuscar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tblEmpregados, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(145)
							.addComponent(btnExcluir)
							.addGap(111)
							.addComponent(btnEditar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(btnBuscar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tblEmpregados, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void limparTabelaEmpregados() {
		tblEmpregados.setModel(new DefaultTableModel(
				new Object[][] {nomesColunas,},nomesColunas));
	}

	protected void atualizarTabelaEmpregados() {
		empregados = controller.consultarTodos();
		limparTabelaEmpregados();
		
		DefaultTableModel modeloTabelaEmpregados = (DefaultTableModel) tblEmpregados.getModel();
		for(Empregado emp: empregados) {
			String[] novaLinha = new String[5];
			novaLinha[0] = emp.getNome();
			novaLinha[1] = emp.getCpf();
			novaLinha[2] = emp.getSexo() == 'M' ? "Masculino" : "Feminino";
			novaLinha[3] = emp.getIdade() + "";
			
			String valorSalarioEmString = String.valueOf(emp.calcularSalario());
			valorSalarioEmString = "R$" + valorSalarioEmString.replace(".", ",");
			
			novaLinha[4] = valorSalarioEmString;
			
			modeloTabelaEmpregados.addRow(novaLinha);
		}
	}
}
