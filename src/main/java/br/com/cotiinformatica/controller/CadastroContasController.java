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
public class CadastroContasController {

	@RequestMapping(value = "/admin/cadastro-contas")
	public ModelAndView cadastroContas() {
		ModelAndView modelAndView = new ModelAndView("admin/cadastro-contas");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/cadastrar-contas", method = RequestMethod.POST)
	public ModelAndView cadastrarContas(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("/admin/cadastro-contas");
		
		try {
			
			//capturando os dados do usuário autenticado
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			Conta conta = new Conta();
			
			conta.setIdConta(UUID.randomUUID());
			conta.setNome(request.getParameter("nome"));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")));
			conta.setValor(Double.parseDouble(request.getParameter("valor")));
			conta.setTipo(Integer.parseInt(request.getParameter("tipo")));
			conta.setDescricao(request.getParameter("descricao"));
			conta.setIdUsuario(usuario.getIdUsuario()); //chave estrangeira
			
			//gravar no banco de dados
			ContaRepository contaRepository = new ContaRepository();
			contaRepository.create(conta);
			
			modelAndView.addObject("mensagem_sucesso", 
					"Parabéns, a conta '"+ conta.getNome() +"' foi cadastrada com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}
