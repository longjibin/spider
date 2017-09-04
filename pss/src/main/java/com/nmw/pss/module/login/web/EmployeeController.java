package com.nmw.pss.module.login.web;

import java.util.List;

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
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String employeeList(Page<Employee> page, Model model){
		List<Employee> list=employeeService.findByPage(page);
		model.addAttribute("list", list);
		return "system/employee/employeelist";
	}
}
