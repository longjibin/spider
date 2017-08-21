package com.nmw.pss.module.system.bean;

import com.nmw.pss.common.base.BaseEntity;

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

	@Override
	public String toString() {
		return "Menu [name=" + name + ", sort=" + sort + ", url=" + url + ", icon=" + icon + ", parentId=" + parentId
				+ ", permissions=" + permissions + ", id=" + id + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", createUserId=" + createUserId + ", updateUserId=" + updateUserId + ", remark="
				+ remark + ", delSign=" + delSign + "]";
	}

}
