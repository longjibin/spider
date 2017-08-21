package com.nmw.pss.common.shiro;

import java.util.ArrayList;
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
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

import com.nmw.pss.common.config.Global;
import com.nmw.pss.common.constant.ShiroConstant;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.bean.EmployeeRole;
import com.nmw.pss.module.login.exception.AccountDisableException;
import com.nmw.pss.module.login.service.EmployeeRoleService;
import com.nmw.pss.module.login.service.EmployeeService;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.bean.RoleMenu;
import com.nmw.pss.module.system.service.MenuService;
import com.nmw.pss.module.system.service.RoleMenuService;
import com.nmw.pss.module.system.service.RoleService;

@Component
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private EmployeeRoleService employeeRoleService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	
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
			//当前用户菜单集合
			List<Menu> menus=new ArrayList<Menu>();
			if(employee.getLoginName().equals(Global.getAdminAccount())){
				/**
				 * 当前用户是管理员
				 */
				
				//查询所有菜单
				menus=menuService.findAll();
			}else{
				/**
				 * 当前用户不是管理员
				 */
				
				Menu menu=null;
				//查询当前用户的角色集合
				List<EmployeeRole> employeeRoles=employeeRoleService.findByEmployeeId(employee.getId());
				
				for (EmployeeRole employeeRole : employeeRoles) {
					Role role=roleService.findById(employeeRole.getRoleId());
					info.addRole(role.getName());
					
					//查询角色关联的菜单集合
					List<RoleMenu> roleMenus=roleMenuService.findByRoleId(employeeRole.getRoleId());
					
					for (RoleMenu roleMenu : roleMenus) {
						menu=menuService.findById(roleMenu.getMenuId());
						menus.add(menu);
					}
				}
			}
			
			//获取当前用户的权限集合
			for (Menu menu : menus) {
				if(StringUtils.isNotBlank(menu.getPermission())){
					for (String permission : StringUtils.split(menu.getPermission(),",")) {
						info.addStringPermission(permission);
					}
				}
			}
			
			return info;
		}
		return null;
	}

}
