package model.dao.lista1;

import java.util.ArrayList;

import model.dao.BaseDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Empregado;

public class EmpregadoDAO implements BaseDAO<Empregado> {

	public Empregado salvar(Empregado novaEntidade) {
		/*
		 *  TODO lembrar de salvar TODOS os atributos na tabela
		 *  EMPREGADO, inclusive o tipo ('O', 'G' ou 'D')
		 */
		return null;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Empregado entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Empregado consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Empregado> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}