package controller.lista1;

import java.util.ArrayList;

import model.bo.lista1.EmpregadoBO;
import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Empregado;
import model.entity.lista1.EmpregadoOperacional;
import model.entity.lista1.Gerente;

/**
 * Classe que contém o controlador das telas de empregado.
 * 
 * @author Vilmar C. Pereira Júnior
 *
 */
public class EmpregadoController {

	private EmpregadoBO bo = new EmpregadoBO();
	
	public EmpregadoController() {
	}
	
	/**
	 * Recebe os dados para salvar um novo empregado,
	 * valida todos os campos e por fim chama 
	 * o BO responsável por chamar o DAO.
	 * @param nome
	 * @param cpf
	 * @param sexoSelecionado
	 * @param idade
	 * @param salarioBruto
	 * @param tipo
	 * @param comissao
	 * 
	 * @return a mensagem de validação dos campos E também o retorno da chamada ao BO.
	 */
	public String salvar(String nome, String cpf, char sexoSelecionado, int idade, String salarioBruto,
			String tipo, String comissao) {
		StringBuilder sb = new StringBuilder();
		
		validarNome(nome, sb);
		validarCPF(cpf, sb);
		
		if(sexoSelecionado == ' ') {
			sb.append("Selecione o sexo \n");
		}
		
		if(idade < 14 || idade > 65) {
			sb.append("Informar uma idade entre 14 e 65 anos \n");
		}
		
		double salarioBrutoDouble = validarValorMonetario(salarioBruto, "Salário bruto", sb);
		Empregado novoEmpregado = validarTipoEmpregado(tipo, comissao, sb);
		
		if(sb.toString().isEmpty()) {
			novoEmpregado.setNome(nome);
			novoEmpregado.setCpf(cpf);
			novoEmpregado.setIdade(idade);
			novoEmpregado.setSexo(sexoSelecionado);
			novoEmpregado.setSalarioBruto(salarioBrutoDouble);
			
			sb = new StringBuilder(bo.salvar(novoEmpregado));
		}
		
		return sb.toString();
	}
	
	public ArrayList<Empregado> consultarTodos(){
		return bo.consultarTodos();
	}
	
	public String excluir(Empregado empregado) {
		return bo.excluir(empregado);
	}
	

	private Empregado validarTipoEmpregado(String tipo, String comissao, StringBuilder sb) {
		double comissaoDouble;
		Empregado novoEmpregado = null;
		if(tipo.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR)) {
			comissaoDouble = validarValorMonetario(comissao, "Comissão", sb);
			novoEmpregado = new Diretor();
			((Diretor) novoEmpregado).setComissao(comissaoDouble);
		}else if(tipo.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR)) {
			comissaoDouble = validarValorMonetario(comissao, "Comissão", sb);
			novoEmpregado = new Gerente();
			((Gerente) novoEmpregado).setComissao(comissaoDouble);
		}else if(tipo.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL)){
			novoEmpregado = new EmpregadoOperacional();
		}else {
			sb.append("Selecione um tipo de empregado \n");
		}
		
		return novoEmpregado;
	}

	private void validarCPF(String cpf, StringBuilder sb) {
		if(cpf == null || cpf.trim().length() != 11) {
			sb.append("CPF deve conter 11 caracteres \n");
		}else {
			try {
				Long.parseLong(cpf);
			}catch(NumberFormatException ex) {
				sb.append("CPF deve conter somente números \n");
			}
		}
	}

	private void validarNome(String nome, StringBuilder sb) {
		if(nome == null || nome.trim().length() < 3) {
			sb.append("Nome deve conter no mínimo 3 caracteres \n");
		}
	}

	private double validarValorMonetario(String valorEmString, String nomeValor, StringBuilder sb) {
		double valorRetorno = -1;
		
		if(valorEmString == null) {
			sb.append("Informar " + nomeValor + "\n");
		}else {
			String[] partes = valorEmString.split(",");
			
			if(partes.length != 2) {
				sb.append("Informar " + nomeValor + " com uma vírgula (ex.: 1500,00) \n");
			}else {
				if(partes[1].length() != 2) {
					sb.append("Informar " + nomeValor + " com duas casas decimais \n");
				}else {
					try {
						valorRetorno = Double.parseDouble(valorEmString.replace(",", "."));
					}catch(NumberFormatException ex) {
						sb.append(nomeValor +" deve conter somente números \n");
					}
				}
			}
		}
		
		return valorRetorno;
	}
}