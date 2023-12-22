package br.com.cotiinformatica.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class CriarUsuarioController {

	/*
	 * Método para abrir a página home.jsp como
	 * página inicial do projeto
	 */
	@RequestMapping(value="/criar-usuario")
	public ModelAndView criarUsuario(HttpServletResponse response) throws IOException{
		return new ModelAndView("criar-usuario");
	}
	
	/*
	 * Método para capturar o SUBMIT (envio do formulário)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/cadastrar-usuario")
	public ModelAndView cadastrarUsuario(HttpServletRequest request) {
		
		//definindo o nome da página para onde o fluxo irá retornar
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		
		try {
			
			Usuario usuario = new Usuario();
			
			//capturando os campos enviados pelo formulário
			usuario.setIdUsuario(UUID.randomUUID());
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			
			//verificar se já existe um usuário cadastrado com o email informado
			if(usuarioRepository.find(usuario.getEmail()) != null) {
				modelAndView.addObject("mensagem_erro", "O email informado já está cadastrado. Por favor, tente outro.");
			}
			else {
				//cadastrando o usuário
				usuarioRepository.create(usuario);
				modelAndView.addObject("mensagem_sucesso", "Usuário cadastrado com sucesso!");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}








