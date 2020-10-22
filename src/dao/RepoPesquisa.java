package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Endereco;
import entidade.Pessoa;
import util.JDBCUtil;

public class RepoPesquisa {
	
public List<Pessoa> pesquisarPessoa(String cpf){
		
		String sql = "select p.cpf cpf,"
				+ "       p.nome nome,"
				+ "       p.idade idade,"
				+ "       p.sexo sexo,"
				+ "       c.numero numero_conta,"
				+ "       c.saldo saldo,"
				+ "       c.limite limite,"
				+ "       e.id_endereco id_endereco,"
				+ "       e.rua rua,"
				+ "       e.numero numero_endereco,"
				+ "       e.complemento complemento"
				+ " from pessoa p"
				+ " left join conta c on c.numero = p.numero_conta"
				+ " left join endereco e on e.id_endereco = p.id_endereco";

		if (cpf != null & !cpf.isEmpty()) {
			sql += " where p.cpf = ? ";
		}

		List<Pessoa> retorno = new ArrayList<Pessoa>();

		Connection conexao;

		try {
			conexao = JDBCUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			if (cpf != null & !cpf.isEmpty()) {
				ps.setString(1, cpf);
			}
			

			ResultSet res = ps.executeQuery();

			while (res.next()) {

				Pessoa p = new Pessoa();
				p.setCpf(res.getString("CPF"));
				p.setNome(res.getString("NOME"));
				p.setIdade(res.getInt("IDADE"));
				p.setSexo(res.getString("SEXO"));
				
				if(res.getInt("ID_NUMERO")> 0) {
					
					Endereco e = new Endereco();
					e.setIdnumero(res.getInt("ID_NUMERO"));
					e.setNumero(res.getInt("NUMERO"));
					e.setRua(res.getString("RUA"));
					e.setComplemento(res.getString("COMPLEMENTO"));
				}
				
				retorno.add(p);		
			

			}

			ps.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return retorno;

	}
}
