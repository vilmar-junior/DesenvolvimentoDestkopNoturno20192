package controller.aula05;

import java.util.ArrayList;

import model.dao.aula05.EnderecoDAO;
import model.entity.aula05.Endereco;

public class EnderecoController {

	public ArrayList<Endereco> consultarTodos() {
		//Como n�o h� regra de neg�cio envolvida, o DAO pode ser chamado diretamente
		EnderecoDAO dao = new EnderecoDAO();
		return dao.consultarTodos();
	}
}