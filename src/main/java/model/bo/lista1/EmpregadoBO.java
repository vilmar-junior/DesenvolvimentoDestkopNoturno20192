package model.bo.lista1;

import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Empregado;

public class EmpregadoBO {

	public String salvar(Empregado novoEmpregado) {
		String mensagem = "";
		
		EmpregadoDAO dao = new EmpregadoDAO();
		
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

}
