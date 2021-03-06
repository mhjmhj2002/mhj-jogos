package com.mhj.jogos.controller;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.jogos.dao.UsuarioDAO;
import com.mhj.jogos.domain.Role;
import com.mhj.jogos.domain.Usuario;

@Controller
@RequestMapping("/")
public class HomeController {

	private Logger LOG = Logger.getLogger(this.getClass());

	 @Autowired
	 private UsuarioDAO usuarioDao;

	 @RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("home");

		return modelAndView;
	}

	@Transactional
	@ResponseBody
//	@RequestMapping("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn")
	public String urlMagicaMaluca() {
		
		LOG.debug("Inicio urlMagicaMaluca");
		
		Usuario usuario = null;

//		try {
//			usuario = usuarioDao.loadUserByUsername("mhjmhj2002@gmail.com");
//		} catch (UsernameNotFoundException e) {
//			usuario = null;
//		}
//		
//		if (usuario != null) {
//			LOG.debug("Fim urlMagicaMaluca");
//			return "Url Mágica executada";
//		}
		
		usuario = new Usuario();
		usuario.setNome("admin");
		usuario.setEmail("mhjmhj2002@gmail.com");
		usuario.setSenha("$2a$08$6.2/OTZIqxTumFGNOSYAlut5To9jwYe6xxuSY/9tbQOAPM71blHuu");
		usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		

		usuarioDao.gravar(usuario);

		LOG.debug("Fim urlMagicaMaluca");

		return "Url Mágica executada";
	}

}
