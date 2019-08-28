package model.bo.lista1;

import java.util.ArrayList;

import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Empregado;

public class EmpregadoBO {
	
	private EmpregadoDAO dao = new EmpregadoDAO();

	public String salvar(Empregado novoEmpregado) {
		String mensagem = "";
		
		if(dao.temCPFCadastrado(novoEmpregado.getCpf())) {
			mensagem = "O CPF informado já pertence a outro empregado";
		}else {
			novoEmpregado = dao.salvar(novoEmpregado);
			
			if(novoEmpregado.getId() > 0) {
				mensagem = "Empregado cadastrado com sucesso";
			}else {
				mensagem = "Erro ao cadastrar empregado. Entre em contato com o administrador do sistema.";
			}
		}
		
		return mensagem;
	}
	
	public ArrayList<Empregado> consultarTodos(){
		return dao.consultarTodos();
	}

	public String excluir(Empregado empregado) {
		String mensagem = "";
		
		if(dao.excluir(empregado.getId())) {
			mensagem = "Empregado " + empregado.getNome() + " foi excluído";
		}else {
			mensagem = "Erro ao excluir o empregado " + empregado.getNome();
		}
		
		return mensagem;
	}
}