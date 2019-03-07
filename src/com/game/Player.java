package com.game;

import java.util.List;

public class Player {
	/**
	 * 用户名
	 */
	private String qqName;
	/**
	 * 用户QQ号
	 */
	private String qqNumber;
	/**
	 * 用户筹码
	 */
	private Double coin;
	/**
	 * 用户的ip地址
	 */
	private String ip;
	/**
	 * 用户拥有的扑克牌
	 */
	private List<Poker> pokerList;
	
	/**
	 * 出牌
	 */
	private void play(List<Poker> otherPorkers,List<Poker> myPorkers){
		
	}
	
	
	
	

	public String getQqName() {
		return qqName;
	}

	public void setQqName(String qqName) {
		this.qqName = qqName;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public Double getCoin() {
		return coin;
	}

	public void setCoin(Double coin) {
		this.coin = coin;
	}

	public List<Poker> getPokerList() {
		return pokerList;
	}

	public void setPokerList(List<Poker> pokerList) {
		this.pokerList = pokerList;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
