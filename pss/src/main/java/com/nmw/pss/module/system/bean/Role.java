package com.nmw.pss.module.system.bean;

import java.util.ArrayList;
import java.util.List;

import com.nmw.pss.common.base.BaseEntity;

/**
 * 角色实体
 * 
 * @author Administrator
 *
 */
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 角色名
	private String name;
	
	/**
	 * 辅助属性
	 * @return
	 */
	private List<Menu> menus=new ArrayList<Menu>();
	//角色是否被选中
	private Boolean selected=false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", id=" + id + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", createUserId=" + createUserId + ", updateUserId=" + updateUserId + ", remark=" + remark
				+ ", delSign=" + delSign + "]";
	}

}
