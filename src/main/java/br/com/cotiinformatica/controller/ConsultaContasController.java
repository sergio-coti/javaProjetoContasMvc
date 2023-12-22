package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class ConsultaContasController {

	@RequestMapping(value = "/admin/consulta-contas")
	public ModelAndView consultaContas() {
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/consultar-contas", method = RequestMethod.POST)
	public ModelAndView consultarContas(HttpServletRequest request) {
	
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		
		try {
			
			//capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			String dtMin = request.getParameter("dataMin");
			String dtMax = request.getParameter("dataMax");
			
			Date dataMin = new SimpleDateFormat("yyyy-MM-dd").parse(dtMin);
			Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(dtMax);
			
			//pesquisando as contas no banco de dados
			ContaRepository contaRepository = new ContaRepository();
			List<Conta> contas = contaRepository.find(dataMin, dataMax, usuario.getIdUsuario());
			
			//enviando a lista para a página
			modelAndView.addObject("contas", contas);			
			modelAndView.addObject("mensagem_sucesso", "Contas obtidas para o filtro selecionado: " + contas.size());
			modelAndView.addObject("dtMin", dtMin);
			modelAndView.addObject("dtMax", dtMax);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}	
	
	@RequestMapping(value = "/admin/exclusao-contas", method = RequestMethod.GET)
	public ModelAndView exclusaoContas(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("admin/consulta-contas");
		
		try {
			
			//capturar o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			//capturar os dados enviados pela URL (querystring)
			UUID idConta = UUID.fromString(request.getParameter("id"));
			String dtMin = request.getParameter("dtMin");
			String dtMax = request.getParameter("dtMax");
			
			//excluindo a conta
			ContaRepository contaRepository = new ContaRepository();
			contaRepository.delete(idConta, usuario.getIdUsuario());
			
			modelAndView.addObject("mensagem_sucesso", "Conta excluída com sucesso.");
			
			//devolvendo os dados da página de consulta
			Date dataMin = new SimpleDateFormat("yyyy-MM-dd").parse(dtMin);
			Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(dtMax);
			
			List<Conta> contas = contaRepository.find(dataMin, dataMax, usuario.getIdUsuario());
			
			modelAndView.addObject("contas", contas);		
			modelAndView.addObject("dtMin", dtMin);
			modelAndView.addObject("dtMax", dtMax);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}


















