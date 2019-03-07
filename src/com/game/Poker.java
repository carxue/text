package com.game;

public class Poker {
	/**
	 * 扑克类型：红桃、黑桃、 方块、梅花、大小王
	 */
	private String pokerType;
	/**
	 * 扑克牌的大小数字
	 */
	private short pokerValue;

	/**
	 * 是否是特殊值-祖牌
	 */
	public boolean isSpecial = false;

	/**
	 * 特殊值的大小-14-A,15-2,king-....可根据特殊 需求赋值
	 */
	private short SpecialValue;

	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public short getSpecialValue() {
		return SpecialValue;
	}

	public void setSpecialValue(short specialValue) {
		SpecialValue = specialValue;
	}

	public String getPokerType() {
		return pokerType;
	}

	public void setPokerType(String pokerType) {
		this.pokerType = pokerType;
	}

	public short getPokerValue() {
		return pokerValue;
	}

	public void setPokerValue(short pokerValue) {
		this.pokerValue = pokerValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poker other = (Poker) obj;
		if (pokerType == null) {
			if (other.pokerType != null) {
				return false;
			}
		} else if (pokerType != other.pokerType) {
			return false;
		}
		if (pokerValue != other.getPokerValue()) {
			return false;
		}
		return true;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (pokerType == null ? 0 : pokerType.hashCode());
		result = prime * result + pokerValue;
		return result;
	}

}
