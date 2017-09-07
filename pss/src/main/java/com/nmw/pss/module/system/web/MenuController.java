package com.nmw.pss.module.system.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nmw.pss.common.base.TreeNode;
import com.nmw.pss.common.constant.HttpConstant;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.service.MenuService;

@Controller
@RequestMapping(value="${adminPath}/menu")
public class MenuController {
	private static final Logger LOGGER=LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;
	
	/**
	 * 菜单列表(忽略登录用户的身份)
	 * @return
	 */
	@RequestMapping(value="list")
	public String menuList(Model model){
		List<Menu> list=menuService.findTreeTable();
		model.addAttribute("list", list);
		return "system/menu/menulist";
	}
	
	/**
	 * 菜单表单
	 * @return
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String menuForm(Menu menu, Model model){
		if (StringUtils.isNotBlank(menu.getId())) {
			//修改菜单
			menu=menuService.findById(menu.getId());
			menu.setParent(menuService.findById(menu.getpId()));
		}else{
			//新增菜单
			if(StringUtils.isNotBlank(menu.getpId())){
				menu.setParent(menuService.findById(menu.getpId()));
			}else{
				menu.setParent(menuService.findById("1"));
			}
			menu.setVisible(Menu.VISIBLE);
			menu.setType(Menu.TYPE_NORMAL);
		}
		model.addAttribute("menu", menu);
		return "system/menu/menuform";
	}
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Map<String, Object> menuSave(Menu menu){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			Menu parent=menuService.findById(menu.getpId());
			menu.setLevel(parent.getLevel()+1);
			menuService.save(menu);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("菜单保存失败", e);
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="remove",method=RequestMethod.GET)
	public Map<String, Object> menuRemove(Menu menu){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			menuService.removeById(menu.getId());
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("菜单删除失败", e);
		}
		return resultMap;
	}
	
	/**
	 * 菜单树
	 * @return
	 */
	@RequestMapping(value="tree",method=RequestMethod.GET)
	public String menuTree(Model model){
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		List<Menu> menus=menuService.findAll();
		for (Menu menu : menus) {
			treeNodes.add(new TreeNode(menu.getId(), menu.getpId(), menu.getName(), false));
		}
		LOGGER.info("treeNodes:"+JSON.toJSONString(treeNodes));
		model.addAttribute("treeNodes", JSON.toJSONString(treeNodes));
		return "system/menu/menutree";
	}
	
	@RequestMapping(value="icon",method=RequestMethod.GET)
	public String menuIcon(){
		return "system/menu/menuicon";
	}
}
