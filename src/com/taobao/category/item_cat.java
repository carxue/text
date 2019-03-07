package com.taobao.category;

public class item_cat {
	/**
	 * 类目id
	 */
	public String cid;
	/**
	 * 是否为父目录
	 */
	public String is_parent;
	/**
	 * 父目录ID
	 */
	public String parent_cid;
	/**
	 * 目录名称
	 */
	public String name;

	public item_cat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public item_cat(String cid, String is_parent, String parent_cid, String name) {
		super();
		this.cid = cid;
		this.is_parent = is_parent;
		this.parent_cid = parent_cid;
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getIs_parent() {
		return is_parent;
	}

	public void setIs_parent(String is_parent) {
		this.is_parent = is_parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "item_cat [cid=" + cid + ", is_parent=" + is_parent
				+ ", parent_cid=" + parent_cid + ", name=" + name + "]";
	}
}
