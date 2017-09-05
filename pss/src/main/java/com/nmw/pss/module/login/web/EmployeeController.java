package com.nmw.pss.module.login.web;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nmw.pss.common.base.Page;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.service.EmployeeService;

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
	
	/**
	 * 员工列表
	 * @return
	 */
	@RequestMapping(value="list",method={RequestMethod.GET, RequestMethod.POST})
	public String employeeList(Employee employee, Page<Employee> page, Model model){
		page.setQueryObj(employee);
		employeeService.findByPage(page);
		model.addAttribute("page", page);
		model.addAttribute("employee", employee);
		return "system/employee/employeelist";
	}
	
	/**
	 * 员工表单
	 * @return
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String employeeForm(Employee employee, Model model){
		if (StringUtils.isNotBlank(employee.getId())) {
			//员工编辑
			employee=employeeService.findById(employee.getId());
		}else{
			//新增员工
			employee.setStatus(Employee.STATUS_ENABLE);
		}
		model.addAttribute("employee", employee);
		return "system/employee/employeeform";
	}
}
