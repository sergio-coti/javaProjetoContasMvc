package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ContaRepository {

	//método para gravar uma conta no banco de dados
	public void create(Conta conta) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo comando SQL
		PreparedStatement statement = connection.prepareStatement
				("insert into conta(idconta, nome, data, valor, tipo, descricao, idusuario) values(?,?,?,?,?,?,?)");
		
		statement.setObject(1, conta.getIdConta());
		statement.setString(2, conta.getNome());
		statement.setDate(3, new java.sql.Date(conta.getData().getTime()));
		statement.setDouble(4, conta.getValor());
		statement.setInt(5, conta.getTipo());
		statement.setString(6, conta.getDescricao());
		statement.setObject(7, conta.getIdUsuario());
		statement.execute();
		
		//fechando a conexão
		connection.close();
	}
	
	//método para atualizar uma conta no banco de dados
	public void update(Conta conta) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("update conta set nome=?, data=?, valor=?, tipo=?, descricao=? where idconta=? and idusuario=?");
		
		statement.setString(1, conta.getNome());
		statement.setDate(2, new java.sql.Date(conta.getData().getTime()));
		statement.setDouble(3, conta.getValor());
		statement.setInt(4, conta.getTipo());
		statement.setString(5, conta.getDescricao());
		statement.setObject(6, conta.getIdConta());
		statement.setObject(7, conta.getIdUsuario());
		statement.execute();
		
		//fechando a conexão
		connection.close();
	}	
	
	//método para excluir uma conta no banco de dados
	public void delete(UUID idConta, UUID idUsuario) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo um comando SQL no banco de dados
		PreparedStatement statement = connection.prepareStatement
				("delete from conta where idconta = ? and idusuario = ?");
		
		statement.setObject(1, idConta);
		statement.setObject(2, idUsuario);
		statement.execute();
		
		connection.close();
	}	
	
	//método para consultar as contas de um usuário dentro de um período de datas
	public List<Conta> find(Date dataMin, Date dataMax, UUID idUsuario) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo um comando SQL no banco de dados
		PreparedStatement statement = connection.prepareStatement
				("select * from conta where data between ? and ? and idusuario = ? order by data ");
		
		statement.setDate(1, new java.sql.Date(dataMin.getTime()));
		statement.setDate(2, new java.sql.Date(dataMax.getTime()));
		statement.setObject(3, idUsuario);
		ResultSet resultSet = statement.executeQuery();
		
		List<Conta> contas = new ArrayList<Conta>();
		
		//percorrer cada registro obtido na consulta
		while(resultSet.next()) {
			
			Conta conta = new Conta();
			
			conta.setIdConta(UUID.fromString(resultSet.getString("idconta")));
			conta.setNome(resultSet.getString("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			conta.setValor(resultSet.getDouble("valor"));
			conta.setTipo(resultSet.getInt("tipo"));
			conta.setDescricao(resultSet.getString("descricao"));
			conta.setIdUsuario(UUID.fromString(resultSet.getString("idusuario")));
			
			contas.add(conta); //adicionando a conta na lista
		}
		
		//fechando a conexão
		connection.close();
		
		//retornar a lista
		return contas;
	}
	
	//método para consultar 1 conta através do id
	public Conta find(UUID idConta) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo um comando SQL no banco de dados
		PreparedStatement statement = connection.prepareStatement
				("select * from conta where idconta = ?");
		
		statement.setObject(1, idConta);
		ResultSet resultSet = statement.executeQuery();
		
		Conta conta = null;
		
		//verificando se algum registro foi obtido
		if(resultSet.next()) {
			
			conta = new Conta();
			
			conta.setIdConta(UUID.fromString(resultSet.getString("idconta")));
			conta.setNome(resultSet.getString("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data")));
			conta.setValor(resultSet.getDouble("valor"));
			conta.setTipo(resultSet.getInt("tipo"));
			conta.setDescricao(resultSet.getString("descricao"));
			conta.setIdUsuario(UUID.fromString(resultSet.getString("idusuario")));
		}
		
		//fechando a conexão
		connection.close();
		
		//retornar o objeto
		return conta;
	}	
	
}



