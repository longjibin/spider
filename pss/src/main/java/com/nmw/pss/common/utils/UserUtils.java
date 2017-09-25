package com.nmw.pss.common.utils;

import org.apache.shiro.SecurityUtils;

import com.nmw.pss.common.config.Global;
import com.nmw.pss.common.constant.ShiroConstant;
import com.nmw.pss.module.system.bean.Employee;

/**
 * 用户工具类
 * @author Administrator
 *
 */
public class UserUtils {
	/**
	 * 保存当前用户
	 */
	public static void saveCurrentUser(Employee employee){
		SecurityUtils.getSubject().getSession().setAttribute(ShiroConstant.LOGIN_USER, employee);
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static Employee getCurrentUser(){
		return (Employee) SecurityUtils.getSubject().getSession().getAttribute(ShiroConstant.LOGIN_USER);
	}
	
	/**
	 * 判断用户是否是系统管理员
	 * @param employee
	 * @return
	 */
	public static Boolean isAdmin(Employee employee){
		if(Global.getAdminAccount().equals(employee.getLoginName())){
			return true;
		}
		return false;
	}
}
