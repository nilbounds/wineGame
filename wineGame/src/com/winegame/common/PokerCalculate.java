package com.winegame.common;

import com.winegame.model.Poker;


/**
 * 扑克牌出牌计算
 * @author suny
 *
 */
public class PokerCalculate {
	
	/**
	 * 出牌
	 * @param site 放牌位置
	 * @return 1 炸弹 2 同花顺 3 顺子 4 同花 5 对子 6 通过
	 */

	public static int checkWin(Poker desk,Poker last){
		System.out.println("桌面牌花色：" + desk.getColor() + "  点数：" + desk.getPoint());
		System.out.println("新牌花色：" + last.getColor() + "  点数：" + last.getPoint());

		if(null != desk && null != last){
			//判断花色是否一样
			if(desk.getColor() == 'e' || desk.getColor() == 'f' || last.getColor() == 'e' || last.getColor() == 'f'){
				return 1;
			}
			int dValue = Math.abs(desk.getPoint() - last.getPoint());
			System.out.println("差值：" + dValue);
			if(desk.getColor() != last.getColor()){
				if(dValue == 1 || dValue == 2 || dValue == 12 || dValue == 11){
					return 3;
				}else if(dValue == 0){
					return 1;
				}else{
					return 6;
				}
			}else{
				//判断是否炸弹
				if(dValue == 0){
					return 1;
				}else{
					if(dValue == 1 || dValue == 2){
						return 2;
					}else{
						return 4;
					}
				}
			}
		}
		return 0;
	}
	
	
}
