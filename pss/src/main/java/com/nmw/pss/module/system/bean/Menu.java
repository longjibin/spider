package com.nmw.pss.module.system.bean;

import java.util.List;

import com.nmw.pss.common.base.BaseEntity;
import com.nmw.pss.module.login.bean.Employee;

/**
 * 菜单实体
 * 
 * @author Administrator
 *
 */
public class Menu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer TYPE_NORMAL = 1;
	public static final Integer TYPE_SYSTEM = 2;

	// 菜单名
	private String name;

	// 菜单序号
	private Integer sort;

	// 链接地址
	private String url;

	// 图标
	private String icon;

	// 父菜单id
	private String parentId;

	// 权限标志（多个权限用,隔开）
	private String permissions;

	// 菜单类型（1表示普通菜单 2表示系统菜单）
	private Integer type;

	// 菜单级数
	private Integer level;

	/**
	 * 辅助属性
	 * 
	 * @return
	 */
	// 当前菜单的子菜单
	private List<Menu> children;

	// 菜单归属人
	private Employee employee;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", sort=" + sort + ", url=" + url + ", icon=" + icon + ", parentId=" + parentId
				+ ", permissions=" + permissions + ", type=" + type + ", level=" + level + ", id=" + id
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", createUserId=" + createUserId
				+ ", updateUserId=" + updateUserId + ", remark=" + remark + ", delSign=" + delSign + "]";
	}

}
