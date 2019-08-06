package model.dao.aula05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.aula05.Telefone;

public class TelefoneDAO implements BaseDAO<Telefone> {

	public Telefone salvar(Telefone novoTelefone) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO TELEFONE (codigoPais, ddd, numero, "
				+ "tipoLinha, idCliente, ativo) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			stmt.setString(1, novoTelefone.getCodigoPais());
			stmt.setString(2, novoTelefone.getDdd());
			stmt.setString(3, novoTelefone.getNumero());
			stmt.setString(4, novoTelefone.getTipoLinha());
			stmt.setInt(5, novoTelefone.getIdCliente());
			stmt.setInt(6, novoTelefone.isAtivo() ? 1 : 0);
			stmt.execute();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				int idGerado = generatedKeys.getInt(1);
				novoTelefone.setId(idGerado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo telefone.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novoTelefone;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Telefone entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Telefone consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Telefone> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}