package model.dao.lista1;

import java.util.ArrayList;

import model.dao.BaseDAO;
import model.entity.lista1.Lotacao;

public class LotacaoDAO implements BaseDAO<Lotacao> {

	public Lotacao salvar(Lotacao novaEntidade) {
		/*
		 *  TODO lembrar de salvar TODOS os atributos na tabela
		 *  LOTACAO, inclusive o ID_LOTACAO_SUPERIOR (gerências tem superior, para diretorias é NULL)
		 */
		return null;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Lotacao entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Lotacao consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Lotacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}