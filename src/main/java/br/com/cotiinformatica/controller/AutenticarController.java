package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class AutenticarController {

	//página inicial do projeto "/"
	@RequestMapping(value = "/")
	public ModelAndView autenticar() {
		//Mapeando a página JSP que será aberta no navegador
		ModelAndView modelAndView = new ModelAndView("autenticar");
		return modelAndView;
	}
	
	//método para capturar o SUBMIT POST da página de autenticação
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(HttpServletRequest request) {
		//Mapeando a página JSP que será aberta no navegador
		ModelAndView modelAndView = new ModelAndView("autenticar");
		
		try {
			
			//resgatar os campos enviados pelo formulário
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			//consultar o usuário no banco de dados através do email e da senha
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.find(email, senha);
			
			//verificar se o usuário foi encontrado
			if(usuario != null) {
				//Gravar os dados do usuário em uma sessão no navegador
				request.getSession().setAttribute("usuario", usuario);
				//redirecionar para a página de boas vindas do sistema
				modelAndView.setViewName("redirect:admin/dashboard");
			}
			else {
				modelAndView.addObject("mensagem_erro", "Acesso negado. Usuário inválido.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		//apagar a sessão do usuário
		request.getSession().removeAttribute("usuario");
		
		//redirecionar de volta para a página inicial do sistema
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/");
		
		return modelAndView;
	}
	
}








