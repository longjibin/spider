package com.nmw.pss.common.base;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 正常状态
	 */
	public static final Integer DEL_NORMAL = 1;
	/**
	 * 逻辑删除状态
	 */
	public static final Integer DEL_DELETE = 2;

	// 主键
	protected String id;

	// 创建时间
	protected Date createTime;
	// 修改时间
	protected Date updateTime;
	// 创建人
	protected String createUserId;
	// 修改人
	protected String updateUserId;
	// 备注
	protected String remark;
	// 删除标记(1正常 2删除)
	protected Integer delSign = DEL_NORMAL;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDelSign() {
		return delSign;
	}

	public void setDelSign(Integer delSign) {
		this.delSign = delSign;
	}

}
