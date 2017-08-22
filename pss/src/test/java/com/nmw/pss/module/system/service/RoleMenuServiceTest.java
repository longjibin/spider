package com.nmw.pss.module.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.system.bean.RoleMenu;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class RoleMenuServiceTest {

	@Autowired
	private RoleMenuService roleMenuService;
	
	@Test
	public void testSave() {
		RoleMenu roleMenu=new RoleMenu();
		roleMenu.setRoleId("1");
		roleMenu.setMenuId("df2a49f1d16f4b91bfd5af339e37a08c");
		roleMenuService.save(roleMenu);
	}

}
