package com.nmw.pss.common.base;

import java.io.Serializable;

/**
 * zTree节点对象
 * 
 * @author Administrator
 *
 */
public class TreeNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String pId;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode(String id, String pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pId=" + pId + ", name=" + name + "]";
	}

}