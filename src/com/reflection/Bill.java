package com.reflection;

import java.io.Serializable;

/**
 * 文件名： Bill.java 
 * 版权： 
 * 创建人： ex_kjkfb_xuek 
 * 文件描述： 
 * 修改时间：2014年2月25日下午09:50:44 
 * 修改内容：
 */
@SuppressWarnings({ "unused", "serial" })
public class Bill extends BillFather implements Serializable {

	private String bill_no;// 票号

	private String rgct_id;// 票据标识

	private String profit_sell_id;// 转让明细id

	private String bill_type;// 票据类型

	private String bill_class;// 票据种类

	private String acceptor;// 承兑人

	private String seller_cust_no;// 转让方客户号

	private String seller_name;// 转让方名字

	private String seller_acct;// 转让方名字

	private String sell_attr;// 转让方性质

	private String sell_type;// 转让方性质

	private String sell_amt_type;// 转让金额类型

	private Double sell_amt;// 转让金额

	private String sell_date_type;// 转让期限类型

	private String sell_apply_date;// 转让申请日

	private String sell_end_date;// 转让到期日

	private String interest_end_date;// 计息到期日

	private Integer interest_days;// 计息天数

	private Double fore_buyer_interest;// 预计实收利息

	private Double buy_rate;// 名义申购利息

	private Double real_rate;// 预期到期收益率

	private Double balance;// 可申购余额

	private String acpt_dt;// 出票日
	
	private String due_dt;//

	private String bill_name;// 票据名称

	private String interest_start_date;// 计息开始日

	private Double min_buy_amt;// 最新申购金额

	private Double max_buy_amt;// 最大申购金额

	private String simple_name;// 产品别名

	private String agency;// 机构名称

	private Integer status;// 产品状态

	private Double bill_value;// 产品面值

	private Double price;// 发行价格

	private Integer relegation;// 产品归属类别

	private Integer risk_level;// 风险等级

	private Integer is_person;// 是否个人允许购买

	private Integer is_agency;// 是否机构允许购买

	private Integer is_plan_revo;// 预约认购是否允许撤单

	private Integer is_start_revo;// 企业还款期是否允许撤单

	private Integer is_check_risk;// 产品是否需要校验风险等级

	private Integer is_person_grp;// 个人允许客户组
	
	

	public String getDue_dt() {
		return due_dt;
	}

	public void setDue_dt(String due_dt) {
		this.due_dt = due_dt;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getRgct_id() {
		return rgct_id;
	}

	public void setRgct_id(String rgct_id) {
		this.rgct_id = rgct_id;
	}

	public String getProfit_sell_id() {
		return profit_sell_id;
	}

	public void setProfit_sell_id(String profit_sell_id) {
		this.profit_sell_id = profit_sell_id;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public String getBill_class() {
		return bill_class;
	}

	public void setBill_class(String bill_class) {
		this.bill_class = bill_class;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	public String getSeller_cust_no() {
		return seller_cust_no;
	}

	public void setSeller_cust_no(String seller_cust_no) {
		this.seller_cust_no = seller_cust_no;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_acct() {
		return seller_acct;
	}

	public void setSeller_acct(String seller_acct) {
		this.seller_acct = seller_acct;
	}

	public String getSell_attr() {
		return sell_attr;
	}

	public void setSell_attr(String sell_attr) {
		this.sell_attr = sell_attr;
	}

	public String getSell_type() {
		return sell_type;
	}

	public void setSell_type(String sell_type) {
		this.sell_type = sell_type;
	}

	public String getSell_amt_type() {
		return sell_amt_type;
	}

	public void setSell_amt_type(String sell_amt_type) {
		this.sell_amt_type = sell_amt_type;
	}

	public Double getSell_amt() {
		return sell_amt;
	}

	public void setSell_amt(Double sell_amt) {
		this.sell_amt = sell_amt;
	}

	public String getSell_date_type() {
		return sell_date_type;
	}

	public void setSell_date_type(String sell_date_type) {
		this.sell_date_type = sell_date_type;
	}

	public String getSell_apply_date() {
		return sell_apply_date;
	}

	public void setSell_apply_date(String sell_apply_date) {
		this.sell_apply_date = sell_apply_date;
	}

	public String getSell_end_date() {
		return sell_end_date;
	}

	public void setSell_end_date(String sell_end_date) {
		this.sell_end_date = sell_end_date;
	}

	public String getInterest_end_date() {
		return interest_end_date;
	}

	public void setInterest_end_date(String interest_end_date) {
		this.interest_end_date = interest_end_date;
	}

	public Integer getInterest_days() {
		return interest_days;
	}

	public void setInterest_days(Integer interest_days) {
		this.interest_days = interest_days;
	}

	public Double getFore_buyer_interest() {
		return fore_buyer_interest;
	}

	public void setFore_buyer_interest(Double fore_buyer_interest) {
		this.fore_buyer_interest = fore_buyer_interest;
	}

	public Double getBuy_rate() {
		return buy_rate;
	}

	public void setBuy_rate(Double buy_rate) {
		this.buy_rate = buy_rate;
	}


	public Double getReal_rate() {
		return real_rate;
	}

	public void setReal_rate(Double real_rate) {
		this.real_rate = real_rate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAcpt_dt() {
		return acpt_dt;
	}

	public void setAcpt_dt(String acpt_dt) {
		this.acpt_dt = acpt_dt;
	}

	public String getBill_name() {
		return bill_name;
	}

	public void setBill_name(String bill_name) {
		this.bill_name = bill_name;
	}

	public String getInterest_start_date() {
		return interest_start_date;
	}

	public void setInterest_start_date(String interest_start_date) {
		this.interest_start_date = interest_start_date;
	}

	public Double getMin_buy_amt() {
		return min_buy_amt;
	}

	public void setMin_buy_amt(Double min_buy_amt) {
		this.min_buy_amt = min_buy_amt;
	}

	public Double getMax_buy_amt() {
		return max_buy_amt;
	}

	public void setMax_buy_amt(Double max_buy_amt) {
		this.max_buy_amt = max_buy_amt;
	}

	public String getSimple_name() {
		return simple_name;
	}

	public void setSimple_name(String simple_name) {
		this.simple_name = simple_name;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getBill_value() {
		return bill_value;
	}

	public void setBill_value(Double bill_value) {
		this.bill_value = bill_value;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRelegation() {
		return relegation;
	}

	public void setRelegation(Integer relegation) {
		this.relegation = relegation;
	}

	public Integer getRisk_level() {
		return risk_level;
	}

	public void setRisk_level(Integer risk_level) {
		this.risk_level = risk_level;
	}

	public Integer getIs_person() {
		return is_person;
	}

	public void setIs_person(Integer is_person) {
		this.is_person = is_person;
	}

	public Integer getIs_agency() {
		return is_agency;
	}

	public void setIs_agency(Integer is_agency) {
		this.is_agency = is_agency;
	}

	public Integer getIs_plan_revo() {
		return is_plan_revo;
	}

	public void setIs_plan_revo(Integer is_plan_revo) {
		this.is_plan_revo = is_plan_revo;
	}

	public Integer getIs_start_revo() {
		return is_start_revo;
	}

	public void setIs_start_revo(Integer is_start_revo) {
		this.is_start_revo = is_start_revo;
	}


	public Integer getIs_check_risk() {
		return is_check_risk;
	}

	public void setIs_check_risk(Integer is_check_risk) {
		this.is_check_risk = is_check_risk;
	}

	public Integer getIs_person_grp() {
		return is_person_grp;
	}

	public void setIs_person_grp(Integer is_person_grp) {
		this.is_person_grp = is_person_grp;
	}

}
