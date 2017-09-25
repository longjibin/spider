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
import com.nmw.pss.common.utils.UserUtils;
import com.nmw.pss.module.system.bean.Employee;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.bean.Role;
import com.nmw.pss.module.system.bean.RoleMenu;
import com.nmw.pss.module.system.service.MenuService;
import com.nmw.pss.module.system.service.RoleMenuService;
import com.nmw.pss.module.system.service.RoleService;

/**
 * 角色控制器
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/role")
public class RoleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;

	/**
	 * 我的角色列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String roleList(Model model) {
		//获取当前登录用户
		Employee employee=UserUtils.getCurrentUser();
		List<Role> list = roleService.findByCE(employee.getId());
		model.addAttribute("list", list);
		model.addAttribute("employee", employee);
		return "system/role/rolelist";
	}
	
	/**
	 * 我管理的角色列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manage/list")
	public String roleManageList(Model model) {
		//获取当前登录用户
		Employee employee=UserUtils.getCurrentUser();
		List<Role> list = roleService.findByCreateUser(employee.getId());
		model.addAttribute("list", list);
		return "system/role/rolemanagelist";
	}
	
	/**
	 * 角色信息编辑
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String roleForm(Role role, Model model) {
		//获取当前登录用户
		Employee employee=UserUtils.getCurrentUser();
		// 查询当前用户拥有的菜单集合
		List<Menu> ownMenus = UserUtils.isAdmin(UserUtils.getCurrentUser()) ? menuService.findAll()
				: menuService.findMenuTreeTableByCE();
		List<Menu> chooseMenus = new ArrayList<Menu>();
		if (StringUtils.isNotBlank(role.getId())) {
			role = roleService.findById(role.getId());
			// 查询当前角色的菜单集合
			List<RoleMenu> roleMenus = roleMenuService.findByRoleId(role.getId());
			for (RoleMenu roleMenu : roleMenus) {
				chooseMenus.add(menuService.findById(roleMenu.getMenuId()));
			}
		}
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		for (Menu menu : ownMenus) {
			Boolean checked=false;
			for (Menu menu2 : chooseMenus) {
				if(menu.getId().equals(menu2.getId())){
					checked=true;
					break;
				}
			}
			treeNodes.add(new TreeNode(menu.getId(), menu.getpId(), menu.getName(), checked));
		}
		model.addAttribute("role", role);
		model.addAttribute("treeNodes", JSON.toJSONString(treeNodes));
		model.addAttribute("employee", employee);
		LOGGER.info(JSON.toJSONString(treeNodes));
		return "system/role/roleform";
	}

	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Map<String, Object> roleSave(Role role, String treeNodes) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String[] menuIds=treeNodes.split(",");
			Menu menu=null;
			for (String menuId : menuIds) {
				menu=new Menu();
				menu.setId(menuId);
				role.getMenus().add(menu);
			}
			//保存角色
			roleService.save(role);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:" + e.getMessage());
			LOGGER.warn("角色保存异常:"+e.getMessage());
		}
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public Map<String, Object> roleRemove(Role role) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//删除角色
			roleService.removeById(role.getId());
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:" + e.getMessage());
			LOGGER.warn("角色删除异常:"+e.getMessage());
		}
		return resultMap;
	}
}
