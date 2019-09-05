package view.exemplos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalculadoraSimples {

	protected static final String OPERACAO_SOMA = "+";
	protected static final String OPERACAO_SUBTRACAO = "-";

	private JFrame frame;
	private JTextField txtVisor;
	private double valor1;
	private double valor2;
	private String operacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraSimples window = new CalculadoraSimples();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculadoraSimples() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtVisor = new JTextField();
		txtVisor.setEditable(false);
		txtVisor.setBounds(6, 20, 418, 39);
		frame.getContentPane().add(txtVisor);
		txtVisor.setColumns(10);

		JButton btnUm = new JButton("1");
		btnUm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoTextoVisor = txtVisor.getText() + "1";
				txtVisor.setText(novoTextoVisor);
			}
		});
		btnUm.setBounds(20, 110, 117, 40);
		frame.getContentPane().add(btnUm);

		JButton btnDois = new JButton("2");
		btnDois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoTextoVisor = txtVisor.getText() + "2";
				txtVisor.setText(novoTextoVisor);
			}
		});
		btnDois.setBounds(150, 110, 118, 40);
		frame.getContentPane().add(btnDois);

		JButton btnTres = new JButton("3");
		btnTres.setBounds(280, 110, 120, 40);
		frame.getContentPane().add(btnTres);

		JButton btnQuatro = new JButton("4");
		btnQuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuatro.setBounds(20, 172, 117, 40);
		frame.getContentPane().add(btnQuatro);

		JButton btnCinco = new JButton("5");
		btnCinco.setBounds(150, 172, 117, 40);
		frame.getContentPane().add(btnCinco);

		JButton btnSeis = new JButton("6");
		btnSeis.setBounds(280, 172, 117, 40);
		frame.getContentPane().add(btnSeis);

		JButton btnSete = new JButton("7");
		btnSete.setBounds(20, 228, 117, 40);
		frame.getContentPane().add(btnSete);

		JButton btnOito = new JButton("8");
		btnOito.setBounds(150, 228, 117, 40);
		frame.getContentPane().add(btnOito);

		JButton btnNove = new JButton("9");
		btnNove.setBounds(280, 228, 117, 40);
		frame.getContentPane().add(btnNove);

		JButton btnZero = new JButton("0");
		btnZero.setBounds(150, 287, 117, 29);
		frame.getContentPane().add(btnZero);

		JButton btnSoma = new JButton("+");

		btnSoma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double valorVisor = Double.parseDouble(txtVisor.getText());

				setOperacao(OPERACAO_SOMA);

				if (getValor1() == 0) {
					setValor1(valorVisor);
					limparVisor();
				} else {
					setValor2(valorVisor);
					setValor1(calcularResultado());
					txtVisor.setText(getValor1() + "");
				}

			}
		});
		btnSoma.setBounds(16, 71, 117, 29);
		frame.getContentPane().add(btnSoma);

		JButton btnSubtracao = new JButton("-");
		btnSubtracao.setBounds(150, 71, 117, 29);
		frame.getContentPane().add(btnSubtracao);

		JButton btnCE = new JButton("CE");
		btnCE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setValor1(0);
				setValor2(0);
				limparVisor();
			}
		});
		btnCE.setBounds(280, 287, 117, 29);
		frame.getContentPane().add(btnCE);

		JButton btnResultado = new JButton("=");
		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valor2 = Double.parseDouble(txtVisor.getText());

				setValor2(valor2);
				double resultado = calcularResultado();

				setValor1(resultado);
				txtVisor.setText(resultado + "");
			}
		});
		btnResultado.setBounds(280, 71, 117, 29);
		frame.getContentPane().add(btnResultado);

	}

	private double calcularResultado() {

		double resultado = 0;
		switch (getOperacao()) {
		case OPERACAO_SOMA:
			resultado = getValor1() + getValor2();
			break;
		case OPERACAO_SUBTRACAO:
			resultado = getValor1() - getValor2();
			break;
		// TODO fazer as demais operações
		default:
			break;
		}

		return resultado;
	}

	private void limparVisor() {
		txtVisor.setText("");
	}

	public double getValor1() {
		return valor1;
	}

	public void setValor1(double valor1) {
		this.valor1 = valor1;
	}

	public double getValor2() {
		return valor2;
	}

	public void setValor2(double valor2) {
		this.valor2 = valor2;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
}
