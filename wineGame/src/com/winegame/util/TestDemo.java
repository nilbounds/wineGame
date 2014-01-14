package com.winegame.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.util.SparseArray;

import com.winegame.model.Poker;
import com.winegame.util.PokerList;
import com.winegame.util.PokerVariable;


/**
 * 扑克池,管理所有游戏中使用到的扑克牌
 * @author Michael
 *
 */
public class TestDemo{

	//当前桌面扑克池
	private List<Poker> deskPokerPool;
	
	//当前剩余扑克
	private List<Poker> surplusPokerPool;
	
	//游戏状态 0 未开始  1 进行中
	private int gameStatus = 0;
	
	//游戏玩家
	private int peopleNum = 0;
	//扑克牌数
	private int pokerSet = 0;
	//扑克数量
	private int pokerNum = 0;

	
	/**
	 * 初始化剩余扑克池
	 * @param peopleNum	参与游戏人数
	 * @param pokerSet	几幅扑克牌
	 */
	public void initPokerPool(int peopleNum,int pokerSet){
		if(null == surplusPokerPool){
			this.surplusPokerPool = new PokerList<Poker>(54 * pokerSet);
			for(int set = 0;set < pokerSet;set++){
				for(int i = 1;i <= 15;i++){
					//填充花色，大小王则
						for(int j = 1;j <= 4;j++){
							Poker poker = new Poker();
							poker.point = i;
							if(i == 14){	
								poker.color = PokerVariable.LITTLEJOKER;
								surplusPokerPool.add(poker);
								break;
							}else if(i == 15){
								poker.color = PokerVariable.BIGJOKER;
								surplusPokerPool.add(poker);
								break;
							}
							//oker.color = j;
							surplusPokerPool.add(poker);
						}
				}
			}
		}
		if(null == deskPokerPool)
			deskPokerPool = new ArrayList<Poker>(peopleNum + 1);
		this.peopleNum = peopleNum;
		this.pokerSet = pokerSet;
		this.pokerNum = pokerSet * 54;
		this.gameStatus = 1;
	}
	
	public static void main(String[] args) {
		TestDemo pokerPool = new TestDemo();
		
//		pokerPool.initPokerPool(5,2);
//		for(int i = 0;i < pokerPool.surplusPokerPool.size();i++){
//			System.out.println("扑克牌花色：" + pokerPool.surplusPokerPool.get(i).color + " 牌面大小：" + pokerPool.surplusPokerPool.get(i).point);
//		}
//		System.out.println("############ "  + pokerPool.peopleNum);
//		System.out.println("牌数量：" + pokerPool.surplusPokerPool.size());
//		System.out.println("桌面牌池大小：" + pokerPool.deskPokerPool.size());
		
//		Random random = new Random();
//		System.out.println((int)(Math.random()*2));
		
		char a = 'a';
		System.out.println((int)a);
		
	}
	/**
	 * 初始发牌
	 */
	public List<Poker> startPork(){
		if(peopleNum != 0 && pokerSet != 0){
			//发牌个数为游戏人数+1，发牌总数量为牌副数 X 54
			for(int i = 0;i < peopleNum + 1;i++){
				Random random = new Random(pokerNum);
				int tempPoint = random.nextInt();
				Random color_Random = new Random(PokerVariable.COLOR_ALLNUM);
			}
		}
		return null;
	}
	/**
	 * 重置游戏状态
	 */
	public void resumes(){
		this.gameStatus = 0;
		surplusPokerPool = null;
		deskPokerPool = null;
	}
	
	/**
	 * 从剩余扑克池中随机获取一张扑克
	 * @param site 放牌位置
	 * @return Poker
	 */
	public Poker getPoker(Integer site){
		if(null != surplusPokerPool && surplusPokerPool.size() > 0){
			Random random = new Random(surplusPokerPool.size());
			int i = random.nextInt();
			Poker poker = surplusPokerPool.get(i);
			surplusPokerPool.remove(i);
			//deskPokerPool.put(site, poker);
			return poker;
		}
		return null;
	}
	
	
}
