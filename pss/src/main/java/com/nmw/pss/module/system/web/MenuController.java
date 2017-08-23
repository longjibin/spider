package com.nmw.pss.module.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="${adminPath}/menu")
public class MenuController {

	@RequestMapping(value="list",method=RequestMethod.GET)
	public String menuList(){
		return "system/menu/menulist";
	}
	
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String menuForm(){
		return "system/menu/menuform";
	}
}
