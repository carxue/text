package com.util;

import java.util.Date;

public class Shop {
	private Long shop_id;
	private Long community_id;
	private String shop_name;
	private Long shop_cat_id;
	private String shop_logo;
	private String detail_addr;
	private String contacts_tel;
	private Long user_id;
	private String shop_intro;
	private Short is_sign;
	private String account_no;
	private Date create_time;
	private Date edit_time;
	private Short status;
	private String create_opt;
	private Short order_food;
	private Short vertify_status;
	private Long apply_id;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Long getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(Long community_id) {
		this.community_id = community_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public Long getShop_cat_id() {
		return shop_cat_id;
	}

	public void setShop_cat_id(Long shop_cat_id) {
		this.shop_cat_id = shop_cat_id;
	}

	public String getShop_logo() {
		return shop_logo;
	}

	public void setShop_logo(String shop_logo) {
		this.shop_logo = shop_logo;
	}

	public String getDetail_addr() {
		return detail_addr;
	}

	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}

	public String getContacts_tel() {
		return contacts_tel;
	}

	public void setContacts_tel(String contacts_tel) {
		this.contacts_tel = contacts_tel;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getShop_intro() {
		return shop_intro;
	}

	public void setShop_intro(String shop_intro) {
		this.shop_intro = shop_intro;
	}

	public Short getIs_sign() {
		return is_sign;
	}

	public void setIs_sign(Short is_sign) {
		this.is_sign = is_sign;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getEdit_time() {
		return edit_time;
	}

	public void setEdit_time(Date edit_time) {
		this.edit_time = edit_time;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCreate_opt() {
		return create_opt;
	}

	public void setCreate_opt(String create_opt) {
		this.create_opt = create_opt;
	}

	public Short getOrder_food() {
		return order_food;
	}

	public void setOrder_food(Short order_food) {
		this.order_food = order_food;
	}

	public Short getVertify_status() {
		return vertify_status;
	}

	public void setVertify_status(Short vertify_status) {
		this.vertify_status = vertify_status;
	}

	public Long getApply_id() {
		return apply_id;
	}

	public void setApply_id(Long apply_id) {
		this.apply_id = apply_id;
	}

	@Override
	public String toString() {
		return "Shop [shop_id=" + shop_id + ", community_id=" + community_id
				+ ", shop_name=" + shop_name + ", shop_cat_id=" + shop_cat_id
				+ ", shop_logo=" + shop_logo + ", detail_addr=" + detail_addr
				+ ", contacts_tel=" + contacts_tel + ", user_id=" + user_id
				+ ", shop_intro=" + shop_intro + ", is_sign=" + is_sign
				+ ", account_no=" + account_no + ", create_time=" + create_time
				+ ", edit_time=" + edit_time + ", status=" + status
				+ ", create_opt=" + create_opt + ", order_food=" + order_food
				+ ", vertify_status=" + vertify_status + ", apply_id="
				+ apply_id + ", title=" + title + "]";
	}

}
