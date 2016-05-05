package com.monitor.server.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.monitor.server.web.service.FacadeService;


/**
 * 
 * @author LJ
 *
 */
@Controller
public class IndexController extends BaseFormController {
	
	@Autowired
	private FacadeService facadeService;
	
	/**
	 * index
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
}
