package com.nmw.pss.module.login.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmw.pss.module.login.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration("classpath:spring-context.xml") //加载配置文件  
public class EmployeeServiceTest {
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeServiceTest.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void testSave() {
		Employee employee=new Employee();
		
		employee.setRemark("bug人员");
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
		employeeService.save(employee);
	}
	
	@Test
	public void testFindById() {
		LOGGER.debug("查询结果:"+employeeService.findById("720a3d10c33e48fcbb1343f50f59384f"));
	}
	
	@Test
	public void testRemoveById() {
		employeeService.removeById("720a3d10c33e48fcbb1343f50f59384f");
	}
	
	@Test
	public void testUpdate(){
		Employee employee=employeeService.findById("c29184d1782c4796992aeef6e87b3127");
		
		employee.setPhone("13251407498");
		employeeService.save(employee);
	}
	
	@Test
	public void testFindByLoginName(){
		System.out.println(employeeService.findEmployeeByLoginName("admin"));
	}

	@Test
	public void testIdCard(){
		String idCardNo="51162219920812521X".replaceAll("[xX]", "0");
		System.out.println(idCardNo.substring(idCardNo.length()-6));
	}
}
