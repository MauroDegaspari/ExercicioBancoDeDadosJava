package dao;

//JAva.sql
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import entidades
import entidade.Endereco;
import entidade.Pessoa;
//import Utils
import util.JDBCUtil;

public class RepositorioCadastro {
	
	private Integer recuperaIdEndereco() {

		String sql = "select S_ID_ENDERECO.nextval from dual";

		Integer idRetorno = null;

		Connection conexao;
		try {
			conexao = JDBCUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			while (res.next()) {

				idRetorno = res.getInt(1);

			}

			ps.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idRetorno;
	}
	
	public boolean inserirEndereco(Endereco endereco) {
		
		String sql ="INSERT INTO ENDERECO(RUA, NUMERO, COMPLEMENTO, ID_ENDERECO)" +
				"VALUES(?,?,?,?)";
		
		Connection conexao;
		
		try {
			conexao = JDBCUtil.getConexao();

			endereco.setIdnumero(this.recuperaIdEndereco());
			
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, endereco.getRua());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.setInt(4, endereco.getIdnumero());
			
			ps.execute();

			ps.close();
			conexao.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean inserirPessoa(Pessoa pessoa) {
		
		String sql = "INSERT INTO PESSOA(NOME, IDADE, SEXO, CPF, ID_ENDERECO)"+
		"VALUES(?,?,?,?,?)" ;
		
		Connection conexao;
		
		try {
			conexao = JDBCUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1,pessoa.getNome());
			ps.setInt(2,pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCpf());
			ps.setInt(5, pessoa.getEndereco().getIdnumero());
			
			ps.execute();

			ps.close();
			conexao.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return false;
	}
}
