package com.game;

import java.util.List;
import java.util.Map;

/**
 * 出牌规则判断
 * 
 * @author xuekui
 * 
 */
public class Rules {
	
	/**
	 * 判断是否为空
	 * @param porkers
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean isNotEmpty(List<Poker> porkers){
		if(porkers!=null&&porkers.size()>0)
			return true;
		return false;
	}
	/**判断list里面的值是否相等
	 * @param porkers
	 * @return
	 */
	public static boolean listIsEqual(List<Poker> porkers){
		Poker poker = porkers.get(0);
		for(int i=1;i<porkers.size();i++){
			if(poker.getPokerValue()!=porkers.get(i).getPokerValue())
				return false;
		}
		return true;
	}
	
	/**
	 * 单个牌时判断大小
	 * @param prefix
	 * @param myself
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean oneValidate(Poker prefix,Poker myself) {
		if(prefix.isSpecial==true && myself.isSpecial==false)
			return false;
		if(prefix.isSpecial==false && myself.isSpecial==true)
			return true;
		if(prefix.isSpecial==true && myself.isSpecial==true){
			if(myself.getSpecialValue()>prefix.getSpecialValue())
				return true;
			return false;
		}
		if(prefix.isSpecial==false && myself.isSpecial==false){
			if(myself.getPokerValue()>prefix.getPokerValue())
				return true;
			return false;
		}
		return false;
	}
	
	/**
	 * 对子牌判断
	 * @param otherPorkers
	 * @param myPorkers
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean doubleValidate(List<Poker> otherPokers,List<Poker> myPokers) {
		if(otherPokers.size()==2 && myPokers.size()==2){
			if(!listIsEqual(otherPokers)||!listIsEqual(myPokers))
				return false;
			return oneValidate(otherPokers.get(0),myPokers.get(0));
		}
		return false;
	}
	
	/**
	 * 三带X判断
	 * @param otherMap
	 * @param myMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean threeValidate(Map<String,List<Poker>> otherMap,Map<String,List<Poker>> myMap) {
		List<Poker> preOther = otherMap.get("prefix");//SUFFIX
		List<Poker> preMy =	myMap.get("prefix");
		List<Poker> sufOther = otherMap.get("suffix");//SUFFIX
		List<Poker> sufMy =	myMap.get("suffix");
		
		if(preOther.size()==3 && preMy.size()==3){//3-0
			if(listIsEqual(preOther)&&listIsEqual(preMy))
				return oneValidate(preOther.get(0),preMy.get(0));
			return false;
		}
		else if(preOther.size()==preMy.size()&&preMy.size()==3&&sufOther.size()==sufMy.size()&&sufMy.size()==1){
			if(listIsEqual(preOther)&&listIsEqual(preMy))//3-1
				return oneValidate(preOther.get(0),preMy.get(0));
			return false;
		}else if(preOther.size()==preMy.size()&&preMy.size()==3&&sufOther.size()==sufMy.size()&&sufMy.size()==2){
			if(listIsEqual(preOther)&&listIsEqual(preMy)&&listIsEqual(sufOther)&&listIsEqual(sufMy))//3-2
				return oneValidate(preOther.get(0),preMy.get(0));
			return false;
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private static boolean fourValidate(List<Poker> otherPokers,List<Poker> myPokers) {
		if(otherPokers.size()==2 && myPokers.size()==2){
			if(!listIsEqual(otherPokers)||!listIsEqual(myPokers))
				return false;
			return oneValidate(otherPokers.get(0),myPokers.get(0));
		}
		return false;
	}
}
