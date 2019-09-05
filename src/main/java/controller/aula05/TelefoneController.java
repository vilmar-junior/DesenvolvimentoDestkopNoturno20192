package controller.aula05;

import java.util.ArrayList;

import model.bo.aula05.TelefoneBO;
import model.entity.aula05.Telefone;

public class TelefoneController {

	public static final String TIPO_TELEFONE_MOVEL = "Móvel";
	public static final String TIPO_TELEFONE_FIXO = "Fixo";

	private TelefoneBO bo = new TelefoneBO();

	public ArrayList<Telefone> consultarTodos() {
		return bo.consultarTodos();
	}

	public ArrayList<Telefone> consultarTelefonesDoCliente(int idCliente) {
		return bo.consultarTelefonesDoCliente(idCliente);
	}

	public String validarNovoTelefone(String codigoPais, String ddd, String numero, String tipoLinha) {
		String mensagem = "";

		mensagem += validarString(codigoPais, "Código do país", 2, 2);
		mensagem += validarString(ddd, "DDD", 2, 2);
		mensagem += validarString(numero, "Número", 8, 12);
		
		if (tipoLinha == null || tipoLinha.isEmpty()) {
			mensagem += "Informe o tipo da linha \n";
		}

		return mensagem;
	}

	private String validarString(String valorDigitado, String nomeDoValor, int tamanhoMinimoAceito, 
			int tamanhoMaximoAceito) {

		String mensagem = "";
		if (valorDigitado.isEmpty() || valorDigitado.trim().length() < tamanhoMinimoAceito
				||  valorDigitado.trim().length() > tamanhoMaximoAceito) {
			mensagem += nomeDoValor + " deve possuir de " +  tamanhoMinimoAceito
					+ " a " + tamanhoMaximoAceito + " caracteres \n";
		}

		return mensagem;
	}

	public String salvar(Telefone novoTelefone) {
		return bo.salvar(novoTelefone);
	}
}
