package com.nmw.pss.module.system.web;

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

import com.nmw.pss.common.constant.HttpConstant;
import com.nmw.pss.module.system.bean.Role;
import com.nmw.pss.module.system.service.RoleService;

/**
 * 角色控制器
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="${adminPath}/role")
public class RoleController {
	private static final Logger LOGGER=LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String roleList(Model model){
		List<Role> list=roleService.findAll();
		model.addAttribute("list", list);
		return "system/role/rolelist";
	}
	
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String roleForm(Role role, Model model){
		if (StringUtils.isNotBlank(role.getId())) {
			role=roleService.findById(role.getId());
		}
		model.addAttribute("role", role);
		return "system/role/roleform";
	}
	
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Map<String, Object> roleSave(Role role){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			roleService.save(role);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("角色保存失败", e);
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="remove",method=RequestMethod.GET)
	public Map<String, Object> roleRemove(Role role){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			roleService.removeById(role.getId());
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("角色删除失败", e);
		}
		return resultMap;
	}
}
