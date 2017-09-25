package com.nmw.pss.module.system.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nmw.pss.common.constant.HttpConstant;
import com.nmw.pss.module.system.bean.EmployeeRole;
import com.nmw.pss.module.system.service.EmployeeRoleService;

@Controller
@RequestMapping(value="${adminPath}/employeerole")
public class EmployeeRoleController {
	private static final Logger LOGGER=Logger.getLogger(EmployeeRoleController.class);
	
	@Autowired
	private EmployeeRoleService employeeRoleService;
	
	/**
	 * 保存员工角色关联
	 * @param employeeRole
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Map<String, Object> employeeSave(EmployeeRole employeeRole){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			//保存员工角色关联
			employeeRoleService.save(employeeRole);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("员工角色保存失败", e);
		}
		return resultMap;
	}
	
	/**
	 * 删除员工角色关联
	 * @param employeeRole
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="remove",method=RequestMethod.GET)
	public Map<String, Object> menuRemove(EmployeeRole employeeRole){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			employeeRoleService.removeByEidAndRid(employeeRole);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("员工角色删除失败", e);
		}
		return resultMap;
	}
}
