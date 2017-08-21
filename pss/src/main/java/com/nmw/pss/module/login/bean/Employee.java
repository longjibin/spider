package com.nmw.pss.module.login.bean;

import com.nmw.pss.common.base.BaseEntity;

/**
 * 员工实体
 * 
 * @author Administrator
 *
 */
public class Employee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer STATUS_ENABLE = 1;
	public static final Integer STATUS_DISABLE = 2;

	// 工号
	private String jobNo;
	// 登录名
	private String loginName;
	// 登录密码
	private String loginPass;
	// 昵称
	private String nickName;
	// 头像
	private String headPic;
	// 邮箱
	private String email;
	// 手机
	private String phone;
	// 座机
	private String tel;
	// 状态(1正常 2禁用)
	private Integer status;

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [jobNo=" + jobNo + ", loginName=" + loginName + ", loginPass=" + loginPass + ", nickName="
				+ nickName + ", headPic=" + headPic + ", email=" + email + ", phone=" + phone + ", tel=" + tel
				+ ", status=" + status + ", id=" + id + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", createUserId=" + createUserId + ", updateUserId=" + updateUserId + ", remark=" + remark
				+ ", delSign=" + delSign + "]";
	}

}
