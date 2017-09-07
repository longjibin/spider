package com.nmw.pss.module.login.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nmw.pss.common.base.Page;
import com.nmw.pss.common.constant.HttpConstant;
import com.nmw.pss.common.utils.UserUtils;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.bean.EmployeeRole;
import com.nmw.pss.module.login.service.EmployeeRoleService;
import com.nmw.pss.module.login.service.EmployeeService;
import com.nmw.pss.module.system.bean.Role;
import com.nmw.pss.module.system.service.RoleService;

/**
 * 员工管理控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/employee")
public class EmployeeController {
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private EmployeeRoleService employeeRoleService;
	
	/**
	 * 员工列表
	 * @return
	 */
	@RequestMapping(value="list")
	public String employeeList(Employee employee, Page<Employee> page, Model model){
		//获取当前登录员工
		Employee current=UserUtils.getCurrentUser();
		page.setQueryObj(employee);
		employeeService.findByPage(page);
		model.addAttribute("page", page);
		model.addAttribute("employee", employee);
		model.addAttribute("current", current);
		return "system/employee/employeelist";
	}
	
	/**
	 * 员工表单
	 * @return
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String employeeForm(Employee employee, Model model){
		//定义员工已有的角色集合
		List<Role> ownRoles=new ArrayList<Role>();
		//查询可选的角色集合
		List<Role> roles = roleService.findAll();
		//获取当前登录员工
		Employee current=UserUtils.getCurrentUser();
		//判断是修改员工还是新增员工
		if (StringUtils.isNotBlank(employee.getId())) {//修改员工
			//查询编辑的员工信息
			employee=employeeService.findById(employee.getId());
			//查询编辑员工的角色集合
			List<EmployeeRole> employeeRoles=employeeRoleService.findByEmployeeId(employee.getId());
			for (EmployeeRole employeeRole : employeeRoles) {
				ownRoles.add(roleService.findById(employeeRole.getRoleId()));
			}
		}else{
			//新增员工
			employee.setStatus(Employee.STATUS_ENABLE);
		}
		for (Role role : roles) {
			for (Role role2 : ownRoles) {
				if(role.getId().equals(role2.getId())){
					role.setSelected(true);
					break;
				}
			}
		}
		model.addAttribute("employee", employee);
		model.addAttribute("current", current);
		model.addAttribute("roles", roles);
		return "system/employee/employeeform";
	}
	
	/**
	 * 保存员工信息
	 * @param employee 员工对象
	 * @param roleIds 角色id，多个角色用,隔开
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Map<String, Object> employeeSave(Employee employee){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(employee.getId())){//修改
				if(StringUtils.isNotBlank(employee.getLoginPass())){
					employee.setLoginPass(DigestUtils.md5Hex(employee.getLoginPass()));
				}
			}else{//新增
				employee.setJobNo(new SimpleDateFormat("yyyyMMdd").format(new Date())+System.currentTimeMillis());
				employee.setLoginPass(DigestUtils.md5Hex(employee.getLoginPass()));
			}
			//保存员工信息以及员工的角色关联
			employeeService.save(employee);
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("员工保存失败", e);
		}
		return resultMap;
	}
	
	/**
	 * 删除员工信息
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="remove",method=RequestMethod.GET)
	public Map<String, Object> menuRemove(Employee employee){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			employeeService.removeById(employee.getId());
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch (Exception e) {
			resultMap.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			resultMap.put(HttpConstant.HTTP_MSG_KEY, "操作失败:"+e.getMessage());
			LOGGER.warn("员工删除失败", e);
		}
		return resultMap;
	}
}
