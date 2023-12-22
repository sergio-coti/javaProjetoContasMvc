package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class EdicaoContasController {

	@RequestMapping(value = "/admin/edicao-contas")
	public ModelAndView edicaoContas(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contas");
		
		try {
			
			//resgatar o ID enviado na URL:
			UUID id = UUID.fromString(request.getParameter("id"));
			
			//consultar os dados da conta no banco de dados
			ContaRepository contaRepository = new ContaRepository();
			Conta conta = contaRepository.find(id);
			
			//enviando os dados da conta para a página
			modelAndView.addObject("conta", conta);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/editar-contas", method = RequestMethod.POST)
	public ModelAndView editarContas(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("/admin/edicao-contas");
		
		try {
			
			//capturando os dados do usuário autenticado
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			Conta conta = new Conta();
			
			conta.setIdConta(UUID.fromString(request.getParameter("idConta")));
			conta.setNome(request.getParameter("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")));
			conta.setValor(Double.parseDouble(request.getParameter("valor")));
			conta.setTipo(Integer.parseInt(request.getParameter("tipo")));
			conta.setDescricao(request.getParameter("descricao"));
			conta.setIdUsuario(usuario.getIdUsuario()); //chave estrangeira
			
			//atualizar no banco de dados
			ContaRepository contaRepository = new ContaRepository();
			contaRepository.update(conta);
			
			modelAndView.addObject("mensagem_sucesso", 
					"Parabéns, a conta '"+ conta.getNome() +"' foi atualizada com sucesso.");
			modelAndView.addObject("conta", conta);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
}
