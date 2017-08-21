package com.nmw.pss.module.login.dao;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.login.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class EmployeeDaoTest {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Test
	public void test() {
		Date date=new Date();
		Employee employee=new Employee();
		employee.setId("1");
		employee.setCreateTime(date);
		employee.setUpdateTime(date);
		employee.setCreateUserId("1");
		employee.setUpdateUserId("1");
		employee.setRemark("备注");
		employee.setDelSign(Employee.DEL_NORMAL);
		employee.setJobNo("20170818");
		employee.setLoginName("admin");
		employee.setLoginPass(DigestUtils.md5Hex("admin"));
		employee.setNickName("admin");
		employee.setHeadPic("");
		employee.setEmail("996835067@qq.com");
		employee.setPhone("18584568356");
		employee.setTel("02368629315");
		employee.setStatus(Employee.STATUS_ENABLE);
		
		employeeDao.insert(employee);
	}

}
