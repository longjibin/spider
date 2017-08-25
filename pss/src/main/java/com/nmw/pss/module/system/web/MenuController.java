package com.nmw.pss.module.system.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.nmw.pss.common.base.TreeNode;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.service.MenuService;

@Controller
@RequestMapping(value="${adminPath}/menu")
public class MenuController {
	private static final Logger LOGGER=LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;
	/**
	 * 菜单列表
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String menuList(){
		return "system/menu/menulist";
	}
	
	/**
	 * 菜单表单
	 * @return
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String menuForm(){
		return "system/menu/menuform";
	}
	
	/**
	 * 菜单树
	 * @return
	 */
	@RequestMapping(value="tree",method=RequestMethod.GET)
	public String menuTree(HttpServletRequest request){
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		List<Menu> menus=menuService.findAll();
		for (Menu menu : menus) {
			treeNodes.add(new TreeNode(menu.getId(), menu.getpId(), menu.getName()));
		}
		LOGGER.info("treeNodes:"+JSON.toJSONString(treeNodes));
		request.setAttribute("treeNodes", JSON.toJSONString(treeNodes));
		return "system/menu/menutree";
	}
	
	@RequestMapping(value="icon",method=RequestMethod.GET)
	public String menuIcon(){
		return "system/menu/menuicon";
	}
}
