package com.winegame.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Application;
import android.util.Log;
import android.util.SparseArray;

import com.winegame.model.Poker;
import com.winegame.util.PokerList;
import com.winegame.util.PokerVariable;


/**
 * 扑克池,管理所有游戏中使用到的扑克牌
 * @author Michael
 *
 */
public class PokerPool extends Application {

	//当前桌面扑克池
	private SparseArray<Poker> deskPokerPool;
	
	//当前剩余扑克
	private PokerList<Poker> surplusPokerPool;
	
	//游戏状态 0 未开始  1 进行中
	private int gameStatus = 0;
	
	//游戏玩家
	private int peopleNum = 0;
	//扑克牌数
	private int pokerSet = 0;
	//扑克数量
	private int pokerNum = 0;

	
	public SparseArray<Poker> getDeskPokerPool() {
		return deskPokerPool;
	}

	public PokerList<Poker> getSurplusPokerPool() {
		return surplusPokerPool;
	}

	public int getGameStatus() {
		return gameStatus;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public int getPokerSet() {
		return pokerSet;
	}

	public int getPokerNum() {
		return pokerNum;
	}

	public void setSurplusPokerPool(PokerList<Poker> surplusPokerPool) {
		this.surplusPokerPool = surplusPokerPool;
	}

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
						
						for(int j = 'a';j <= 'd';j++){
							Poker poker = new Poker();
							poker.point = i;
							if(i == 14){	
								poker.color = PokerVariable.LITTLEJOKER;
								poker.point = -1;
								surplusPokerPool.add(poker);
								break;
							}else if(i == 15){
								poker.color = PokerVariable.BIGJOKER;
								poker.point = -1;
								surplusPokerPool.add(poker);
								break;
							}
							poker.color = (char)j;
							System.out.println("当前颜色：" + poker.color);
							surplusPokerPool.add(poker);
						}
				}
			}
		}
		for(Poker poker : surplusPokerPool){
			System.out.println("扑克花色："  + poker.getColor() + "  扑克点数：" + poker.getPoint());
		}
		if(null == deskPokerPool)
			deskPokerPool = new SparseArray<Poker>(peopleNum + 1);
		this.peopleNum = peopleNum;
		this.pokerSet = pokerSet;
		this.pokerNum = pokerSet * 54;
		this.gameStatus = 1;
	}
	
	/**
	 * 初始发牌，根据游戏人数，从扑克池中随机选取游戏人数+1张扑克牌
	 * @return 初始牌面扑克牌
	 */
	public void startPork(){
		if(peopleNum > 0 && pokerSet > 0){
			//发牌个数为游戏人数+1，发牌总数量为牌副数 X 54
			for(int i = 0;i < peopleNum + 1;i++){
				Random random = new Random();
				
				int index = random.nextInt(surplusPokerPool.size());
				Log.d("============== 牌池大小：",String.valueOf(surplusPokerPool.size()) + "  随机数：" + index);
				Poker poker = surplusPokerPool.get(index);
				if(poker.getPoint() == -1){
					continue;
				}
				deskPokerPool.put(i, poker);
				surplusPokerPool.remove(poker);
			}
		}
	}
	/**
	 * 重置游戏状态
	 */
	public void resumes(){
		this.gameStatus = 0;
		this.surplusPokerPool = null;
		this.deskPokerPool = null;
	}
	
	/**
	 * 从剩余扑克池中随机获取一张扑克
	 * @param site 放牌位置
	 * @return Poker
	 */
	public Poker getPoker(){
		if(null != surplusPokerPool && surplusPokerPool.size() > 0){
			Random random = new Random();
			int i = random.nextInt(surplusPokerPool.size());
			System.out.println("取牌前池内大小：" + surplusPokerPool.size());
			Poker poker = surplusPokerPool.get(i);
			if(poker.getPoint() == 14 || poker.getPoint() == 15){
				this.getPoker();
			}
			surplusPokerPool.remove(i);
			System.out.println("取牌后池内大小：" + surplusPokerPool.size());
			return poker;
		}
		return null;
	}
	
	
}
