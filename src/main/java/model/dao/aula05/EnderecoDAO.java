package model.dao.aula05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.aula05.Endereco;
import model.entity.aula05.Telefone;

public class EnderecoDAO implements BaseDAO<Endereco> {

	public Endereco salvar(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO(RUA, CEP, ESTADO, CIDADE, BAIRRO, NUMERO) "
				+ " VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novoEndereco.getRua());
			stmt.setString(2, novoEndereco.getCep());
			stmt.setString(3, novoEndereco.getEstado());
			stmt.setString(4, novoEndereco.getCidade());
			stmt.setString(5, novoEndereco.getBairro());
			stmt.setString(6, novoEndereco.getNumero());
			
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novoEndereco.setId(idGerado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereço.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoEndereco;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Endereco consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, rua, cep, estado, cidade, bairro, numero "
				+ " FROM ENDERECO "
				+ " WHERE ID=" + id;
		
		Statement stmt = Banco.getStatement(conn); 
		Endereco endereco = null;
	
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			if(resultadoDaConsulta.next()) {
				endereco = construirEnderecoDoResultSet(resultadoDaConsulta);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar endereço por id = " + id);
			System.out.println("Erro: " + e.getMessage());
		}
		
		return endereco;
	}

	public ArrayList<Endereco> consultarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT id, rua, cep, estado, cidade, bairro, numero "
				+ " FROM ENDERECO ";
		
		Statement stmt = Banco.getStatement(conn); 
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			ResultSet resultadoDaConsulta = stmt.executeQuery(sql);
			while(resultadoDaConsulta.next()) {
				Endereco endereco = construirEnderecoDoResultSet(resultadoDaConsulta);
				enderecos.add(endereco);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar endereços");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return enderecos;
	}
	
	private Endereco construirEnderecoDoResultSet(ResultSet resultadoDaConsulta) {
		Endereco novoEndereco = null;
		try {
			novoEndereco = new Endereco();
			novoEndereco.setId(resultadoDaConsulta.getInt("id"));
			novoEndereco.setRua(resultadoDaConsulta.getString("rua"));
			novoEndereco.setCep(resultadoDaConsulta.getString("cep"));
			novoEndereco.setEstado(resultadoDaConsulta.getString("estado"));
			novoEndereco.setCidade(resultadoDaConsulta.getString("cidade"));
			novoEndereco.setNumero(resultadoDaConsulta.getString("numero"));
			novoEndereco.setBairro(resultadoDaConsulta.getString("bairro"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir endereço a partir do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoEndereco;
	}
}