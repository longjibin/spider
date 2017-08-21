package com.nmw.pss.module.login.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nmw.pss.common.constant.HttpConstant;
import com.nmw.pss.common.constant.ShiroConstant;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.exception.AccountDisableException;
import com.nmw.pss.module.login.service.EmployeeService;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.service.MenuService;

@Controller
public class LoginController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="tologin",method=RequestMethod.GET)
	public String toLogin(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.POST)
	public Map<String, Object> login(Employee employee){
		Map<String, Object> result=new HashMap<String, Object>();
		Subject subject=SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(employee.getLoginName(), DigestUtils.md5Hex(employee.getLoginPass())));
			//登陆成功，保存当前登录用户
			subject.getSession().setAttribute(ShiroConstant.LOGIN_USER, employeeService.findEmployeeByLoginName(employee.getLoginName()));
			result.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_200);
			result.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_200);
		} catch(UnknownAccountException e){
			result.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_1001);
			result.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_1001);
		} catch(IncorrectCredentialsException e){
			result.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_1000);
			result.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_1000);
		} catch (AccountDisableException e) {
			result.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_1002);
			result.put(HttpConstant.HTTP_MSG_KEY, HttpConstant.HTTP_MSG_1002);
		} catch (Exception e) {
			result.put(HttpConstant.HTTP_CODE_KEY, HttpConstant.HTTP_CODE_500);
			result.put(HttpConstant.HTTP_MSG_KEY, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 跳转到管理主页面
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String admin(HttpServletRequest request){
		//查询当前用户的菜单集合
		Subject subject = SecurityUtils.getSubject();
		Employee employee=(Employee) subject.getSession().getAttribute(ShiroConstant.LOGIN_USER);
		List<Menu> menus=menuService.findCurrentEmployeeMenus(employee);
		request.setAttribute("menus", menus);
		return "admin";
	}
}
