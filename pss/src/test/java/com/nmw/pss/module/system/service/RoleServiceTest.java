package com.nmw.pss.module.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.system.bean.Role;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Test
	public void testSave() {
		Role role=new Role();
		role.setName("系统管理员");
		roleService.save(role);
	}
	
	@Test
	public void testFindById() {
//		Role role=new Role();
//		role.setId("1");
		System.out.println(roleService.findById("1"));
		
	}
}
