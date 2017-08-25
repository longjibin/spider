package com.nmw.pss.module.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.system.bean.Menu;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class MenuServiceTest {

	@Autowired
	private MenuService menuService;
	
	@Test
	public void testSave() {
		Menu menu=new Menu();
//		menu.setName("顶级菜单");
//		menu.setSort(0);
//		menu.setUrl("");
//		menu.setIcon("");
//		menu.setParentId("");
		
		menu.setName("权限管理");
		menu.setSort(10);
		menu.setUrl("");
		menu.setIcon("");
		menu.setpId("0caeae1b80c74879a94352e8842b8c21");
		menu.setPermissions("");
		menu.setType(Menu.TYPE_SYSTEM);
		menu.setLevel(2);
		menuService.save(menu);
	}

}
