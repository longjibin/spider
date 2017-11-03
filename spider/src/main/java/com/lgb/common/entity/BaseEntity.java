package com.lgb.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源实体
 * @author Administrator
 *
 * @date 2017年10月25日
 */
public class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 资源id
	 */
	protected String id;
	
	/**
	 * 资源创建时间
	 */
	protected Date createDateTime;
	
	/**
	 * 最近更新时间
	 */
	protected Date updateDateTime;

	public BaseEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
