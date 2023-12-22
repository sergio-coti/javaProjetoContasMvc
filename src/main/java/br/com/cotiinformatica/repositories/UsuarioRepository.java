package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class UsuarioRepository {

	//método para cadastrar um usuário no banco de dados
	public void create(Usuario usuario) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo o comando SQL para gravar o usuário no banco de dados
		PreparedStatement statement = connection.prepareStatement
				("insert into usuario(idusuario, nome, email, senha) values(?, ?, ?, md5(?))");
		
		statement.setObject(1, usuario.getIdUsuario());
		statement.setString(2, usuario.getNome());
		statement.setString(3, usuario.getEmail());
		statement.setString(4, usuario.getSenha());
		statement.execute();
		
		connection.close();
	}
	
	//método para consultar 1 usuário no banco de dados através do email
	public Usuario find(String email) throws Exception {
	
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo o comando SQL para consultar o usuário no banco de dados
		PreparedStatement statement = connection.prepareStatement
				("select idusuario, nome, email from usuario where email = ?");
		
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();
		
		Usuario usuario = null;
		
		//verificar se algum usuário foi encontrado
		if(resultSet.next()) {
			
			usuario = new Usuario();
			usuario.setIdUsuario(UUID.fromString(resultSet.getString("idusuario")));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
		}
		
		connection.close();
		return usuario;
	}		
	
	//método para consultar 1 usuário através do email e da senha
	public Usuario find(String email, String senha) throws Exception {
		
		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();
		
		//escrevendo o comando SQL para execução no banco de dados
		PreparedStatement statement = connection.prepareStatement
				("select idusuario, nome, email from usuario where email = ? and senha = md5(?)");
		statement.setString(1, email);
		statement.setString(2, senha);
		ResultSet resultSet = statement.executeQuery();
		
		Usuario usuario = null;
		
		//verificando se algum registro foi encontrado
		if(resultSet.next()) {
			
			usuario = new Usuario();
			usuario.setIdUsuario(UUID.fromString(resultSet.getString("idusuario")));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
		}
		
		connection.close();
		return usuario;
	}	
}




