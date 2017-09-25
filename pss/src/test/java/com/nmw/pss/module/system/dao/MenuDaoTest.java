package com.nmw.pss.module.system.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.system.bean.Employee;
import com.nmw.pss.module.system.bean.Menu;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class MenuDaoTest {

	@Test
	public void testSelectChildrenByModel() {
		Menu query=new Menu();
		Employee employee=new Employee();
		employee.setId("2");
		query.setEmployee(employee);
		query.setpId("0");
	}

}
