package com.nmw.pss.module.login.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.login.bean.EmployeeRole;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class EmployeeRoleServiceTest {

	@Autowired
	private EmployeeRoleService employeeRoleService;
	
	@Test
	public void testSave() {
		EmployeeRole employeeRole=new EmployeeRole();
		employeeRole.setEmployeeId("1");
		employeeRole.setRoleId("1");
		employeeRoleService.save(employeeRole);
	}

	@Test
	public void testFindById() {
		System.out.println(employeeRoleService.findById("709aa237c61248f6aae140199d9d0e17"));
	}
	
	@Test
	public void testRemoveById(){
		employeeRoleService.removeById("709aa237c61248f6aae140199d9d0e17");
		for (EmployeeRole employeeRole : employeeRoleService.findAll()) {
			System.out.println(employeeRole);
		}
	}
	
}
