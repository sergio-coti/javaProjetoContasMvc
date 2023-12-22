package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class DashboardController {

	@RequestMapping(value = "/admin/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		
		//mapeamento da página JSP que será aberta
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		
		try {
			
			//capturar o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
			// Obtendo a data do primeiro dia do mês atual e do ultimo dia do mês atual
	        LocalDate dataAtual = LocalDate.now();
	        LocalDate primeiroDiaDoMes = dataAtual.with(TemporalAdjusters.firstDayOfMonth());
	        LocalDate ultimoDiaDoMes = dataAtual.with(TemporalAdjusters.lastDayOfMonth());
			
	        //convertendo para java.util.Date
	        java.util.Date dataMin = java.sql.Date.valueOf(primeiroDiaDoMes);
	        java.util.Date dataMax = java.sql.Date.valueOf(ultimoDiaDoMes);
	        
	        //enviando as datas para a página:
	        modelAndView.addObject("dtMin", new SimpleDateFormat("yyyy-MM-dd").format(dataMin));
	        modelAndView.addObject("dtMax", new SimpleDateFormat("yyyy-MM-dd").format(dataMax));
	        
	        //consultando as contas
	        ContaRepository contaRepository = new ContaRepository();
	        List<Conta> contas = contaRepository.find(dataMin, dataMax, usuario.getIdUsuario());
	        
	        //calculando o total de contas a receber e a pagar
	        Double totalReceber = 0.0;
	        Double totalPagar = 0.0;
	        
	        for(Conta conta : contas) {
	        	if(conta.getTipo() == 1) //conta a receber
	        		totalReceber += conta.getValor();
	        	else if(conta.getTipo() == 2) //conta a pagar
	        		totalPagar += conta.getValor();
	        }
	        
	        //enviando os dados para a página
	        modelAndView.addObject("totalReceber", totalReceber);
	        modelAndView.addObject("totalPagar", totalPagar);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/filtrardados", method = RequestMethod.POST)
	public ModelAndView filtrarDados(HttpServletRequest request) {
		
		//mapeamento da página JSP que será aberta
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		
		try {
			
			//capturar o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			
	        //capturando as datas
	        Date dataMin = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataMin"));
	        Date dataMax = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataMax"));
	        
	        //enviando as datas para a página:
	        modelAndView.addObject("dtMin", new SimpleDateFormat("yyyy-MM-dd").format(dataMin));
	        modelAndView.addObject("dtMax", new SimpleDateFormat("yyyy-MM-dd").format(dataMax));
	        
	        //consultando as contas
	        ContaRepository contaRepository = new ContaRepository();
	        List<Conta> contas = contaRepository.find(dataMin, dataMax, usuario.getIdUsuario());
	        
	        //calculando o total de contas a receber e a pagar
	        Double totalReceber = 0.0;
	        Double totalPagar = 0.0;
	        
	        for(Conta conta : contas) {
	        	if(conta.getTipo() == 1) //conta a receber
	        		totalReceber += conta.getValor();
	        	else if(conta.getTipo() == 2) //conta a pagar
	        		totalPagar += conta.getValor();
	        }
	        
	        //enviando os dados para a página
	        modelAndView.addObject("totalReceber", totalReceber);
	        modelAndView.addObject("totalPagar", totalPagar);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}
