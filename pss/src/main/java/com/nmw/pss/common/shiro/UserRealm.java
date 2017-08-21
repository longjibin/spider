package com.nmw.pss.common.shiro;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nmw.pss.common.constant.ShiroConstant;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.exception.AccountDisableException;
import com.nmw.pss.module.login.service.EmployeeService;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.service.MenuService;

@Component
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String loginName=token.getUsername();
		Employee selectEmployee=employeeService.findEmployeeByLoginName(loginName);
		if(selectEmployee!=null){
			if(Employee.STATUS_DISABLE.equals(selectEmployee.getStatus())){
				throw new AccountDisableException("当前用户禁止登陆");
			}
			return new SimpleAuthenticationInfo(selectEmployee.getLoginName(), selectEmployee.getLoginPass(), getName());
		}
		return null;
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Subject subject = SecurityUtils.getSubject();
		Employee employee=(Employee) subject.getSession().getAttribute(ShiroConstant.LOGIN_USER);
		if(employee!=null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> menus=menuService.findCurrentEmployeeMenus(employee);
			//获取当前用户的权限集合
			for (Menu menu : menus) {
				if(StringUtils.isNotBlank(menu.getPermissions())){
					for (String permission : StringUtils.split(menu.getPermissions(),",")) {
						info.addStringPermission(permission);
					}
				}
			}
			return info;
		}
		return null;
	}

}
