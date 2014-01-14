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
 * �˿˳�,����������Ϸ��ʹ�õ����˿���
 * @author Michael
 *
 */
public class PokerPool extends Application {

	//��ǰ�����˿˳�
	private SparseArray<Poker> deskPokerPool;
	
	//��ǰʣ���˿�
	private PokerList<Poker> surplusPokerPool;
	
	//��Ϸ״̬ 0 δ��ʼ  1 ������
	private int gameStatus = 0;
	
	//��Ϸ���
	private int peopleNum = 0;
	//�˿�����
	private int pokerSet = 0;
	//�˿�����
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
	 * ��ʼ��ʣ���˿˳�
	 * @param peopleNum	������Ϸ����
	 * @param pokerSet	�����˿���
	 */
	public void initPokerPool(int peopleNum,int pokerSet){
		if(null == surplusPokerPool){
			this.surplusPokerPool = new PokerList<Poker>(54 * pokerSet);
			for(int set = 0;set < pokerSet;set++){
				for(int i = 1;i <= 15;i++){
					//��仨ɫ����С����
						
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
							System.out.println("��ǰ��ɫ��" + poker.color);
							surplusPokerPool.add(poker);
						}
				}
			}
		}
		for(Poker poker : surplusPokerPool){
			System.out.println("�˿˻�ɫ��"  + poker.getColor() + "  �˿˵�����" + poker.getPoint());
		}
		if(null == deskPokerPool)
			deskPokerPool = new SparseArray<Poker>(peopleNum + 1);
		this.peopleNum = peopleNum;
		this.pokerSet = pokerSet;
		this.pokerNum = pokerSet * 54;
		this.gameStatus = 1;
	}
	
	/**
	 * ��ʼ���ƣ�������Ϸ���������˿˳������ѡȡ��Ϸ����+1���˿���
	 * @return ��ʼ�����˿���
	 */
	public void startPork(){
		if(peopleNum > 0 && pokerSet > 0){
			//���Ƹ���Ϊ��Ϸ����+1������������Ϊ�Ƹ��� X 54
			for(int i = 0;i < peopleNum + 1;i++){
				Random random = new Random();
				
				int index = random.nextInt(surplusPokerPool.size());
				Log.d("============== �Ƴش�С��",String.valueOf(surplusPokerPool.size()) + "  �������" + index);
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
	 * ������Ϸ״̬
	 */
	public void resumes(){
		this.gameStatus = 0;
		this.surplusPokerPool = null;
		this.deskPokerPool = null;
	}
	
	/**
	 * ��ʣ���˿˳��������ȡһ���˿�
	 * @param site ����λ��
	 * @return Poker
	 */
	public Poker getPoker(){
		if(null != surplusPokerPool && surplusPokerPool.size() > 0){
			Random random = new Random();
			int i = random.nextInt(surplusPokerPool.size());
			System.out.println("ȡ��ǰ���ڴ�С��" + surplusPokerPool.size());
			Poker poker = surplusPokerPool.get(i);
			if(poker.getPoint() == 14 || poker.getPoint() == 15){
				this.getPoker();
			}
			surplusPokerPool.remove(i);
			System.out.println("ȡ�ƺ���ڴ�С��" + surplusPokerPool.size());
			return poker;
		}
		return null;
	}
	
	
}
