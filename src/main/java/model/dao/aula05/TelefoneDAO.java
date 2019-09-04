package model.dao.aula05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.aula05.Cliente;
import model.entity.aula05.Telefone;

public class TelefoneDAO implements BaseDAO<Telefone> {

	public Telefone salvar(Telefone novoTelefone) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO TELEFONE (codigoPais, ddd, numero, tipoLinha, idCliente, ativo) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, novoTelefone.getCodigoPais());
			stmt.setString(2, novoTelefone.getDdd());
			stmt.setString(3, novoTelefone.getNumero());
			stmt.setString(4, novoTelefone.getTipoLinha());

			if (novoTelefone.getCliente() != null) {
				stmt.setInt(5, novoTelefone.getCliente().getId());
			} else {
				stmt.setInt(5, 0);
			}

			stmt.setInt(6, novoTelefone.isAtivo() ? 1 : 0);
			stmt.execute();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
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
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM TELEFONE WHERE ID=" + id;
		Statement stmt = Banco.getStatement(conn);

		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir telefone.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeLinhasAfetadas > 0;
	}

	/**
	 * Associa e ativa uma lista de telefones a um determinado cliente.
	 * 
	 * @param dono      o cliente que possui os telefones
	 * @param telefones a lista de telefones
	 */
	public void ativarTelefones(Cliente dono, ArrayList<Telefone> telefones) {
		for (Telefone t : telefones) {
			t.setCliente(dono);
			t.setAtivo(true);
			if (t.getId() > 0) {
				// UPDATE no Telefone
				alterar(t);
			} else {
				// INSERT no Telefone
				salvar(t);
			}
		}
	}

	/**
	 * Desativa todos os telefones de um determinado cliente.
	 * 
	 * @param idCliente a chave primária do cliente
	 */
	public void desativarTelefones(int idCliente) {
		Connection conn = Banco.getConnection();
		String sql = " UPDATE TELEFONE " + " SET idCliente=0, ativo=0 " + " WHERE IDCLIENTE=? ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int quantidadeLinhasAfetadas = 0;

		try {
			stmt.setInt(1, idCliente);
			quantidadeLinhasAfetadas = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao desativar telefone.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public boolean alterar(Telefone telefone) {
		Connection conn = Banco.getConnection();
		String sql = " UPDATE TELEFONE " + " SET codigoPais=?, ddd=?, numero=?, tipoLinha=?, idCliente=?, ativo=? "
				+ " WHERE ID=? ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		int quantidadeLinhasAfetadas = 0;

		try {
			stmt.setString(1, telefone.getCodigoPais());
			stmt.setString(2, telefone.getDdd());
			stmt.setString(3, telefone.getNumero());
			stmt.setString(4, telefone.getTipoLinha());

			if (telefone.getCliente() != null) {
				stmt.setInt(5, telefone.getCliente().getId());
			}

			stmt.setInt(6, telefone.isAtivo() ? 1 : 0);
			stmt.setInt(7, telefone.getId());
			quantidadeLinhasAfetadas = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar telefone.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeLinhasAfetadas > 0;
	}

	public Telefone consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, codigoPais, ddd, numero, tipoLinha, idCliente, ativo " + " FROM TELEFONE "
				+ " WHERE ID=" + id;

		Statement stmt = Banco.getStatement(conn);

		Telefone telefone = null;
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);

			if (resultadoDaConsulta.next()) {
				telefone = construirTelefoneDoResultSet(resultadoDaConsulta);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefone por id = " + id);
			System.out.println("Erro: " + e.getMessage());
		}

		return telefone;
	}

	public ArrayList<Telefone> consultarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, codigoPais, ddd, numero, tipoLinha, idCliente, ativo " + " FROM TELEFONE ";

		Statement stmt = Banco.getStatement(conn);
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);

			while (resultadoDaConsulta.next()) {
				Telefone telefone = construirTelefoneDoResultSet(resultadoDaConsulta);
				telefones.add(telefone);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefones");
			System.out.println("Erro: " + e.getMessage());
		}

		return telefones;
	}

	public ArrayList<Telefone> consultarTodosPorIdCliente(int idCliente) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, codigoPais, ddd, numero, tipoLinha, idCliente, ativo " + " FROM TELEFONE "
				+ " WHERE IDCLIENTE = " + idCliente;

		Statement stmt = Banco.getStatement(conn);
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);

			while (resultadoDaConsulta.next()) {
				Telefone telefone = construirTelefoneDoResultSet(resultadoDaConsulta);
				telefones.add(telefone);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefones por idCliente. Idcliente: " + idCliente);
			System.out.println("Erro: " + e.getMessage());
		}

		return telefones;
	}

	private Telefone construirTelefoneDoResultSet(ResultSet resultadoDaConsulta) {
		Telefone telefone;
		telefone = new Telefone();
		try {
			telefone.setId(resultadoDaConsulta.getInt("id"));

			ClienteDAO cDAO = new ClienteDAO();
			Cliente donoDoTelefone = cDAO.consultarPorId(resultadoDaConsulta.getInt("idCliente"));
			telefone.setCliente(donoDoTelefone);
			telefone.setCodigoPais(resultadoDaConsulta.getString("codigoPais"));
			telefone.setDdd(resultadoDaConsulta.getString("ddd"));
			telefone.setNumero(resultadoDaConsulta.getString("numero"));
			telefone.setTipoLinha(resultadoDaConsulta.getString("tipoLinha"));
			telefone.setAtivo(resultadoDaConsulta.getBoolean("ativo"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir telefone a partir do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}

		return telefone;
	}

	public boolean telefoneJaCadastrado(Telefone novoTelefone) {
		String sql = " SELECT ID FROM TELEFONE T " + " WHERE T.CODIGOPAIS = " + novoTelefone.getCodigoPais()
				+ " AND T.DDD = " + novoTelefone.getDdd() + " AND T.NUMERO = " + novoTelefone.getNumero();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet rs = null;
		boolean telefoneJaCadastrado = false;
		try {
			rs = stmt.executeQuery(sql);
			telefoneJaCadastrado = rs.next();

		} catch (SQLException e) {
			System.out.println("Erro ao verificar se telefone já está cadastrado " + novoTelefone);
			System.out.println("Causa: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return telefoneJaCadastrado;
	}
}