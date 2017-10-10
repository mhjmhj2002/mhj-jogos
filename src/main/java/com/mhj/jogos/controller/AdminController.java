package com.mhj.jogos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		
		ModelAndView modelAndView = new ModelAndView("/admin/home");
		
		return modelAndView;
	}
	
	@RequestMapping("/dadosLotofacil")
	public ModelAndView dadosLotofacil() {
		ModelAndView modelAndView = new ModelAndView("jogo/lotofacil/atualizaDados");
		return modelAndView;
	}

}
