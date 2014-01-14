package com.winegame.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.util.SparseArray;

import com.winegame.model.Poker;
import com.winegame.util.PokerList;
import com.winegame.util.PokerVariable;


/**
 * �˿˳�,����������Ϸ��ʹ�õ����˿���
 * @author Michael
 *
 */
public class TestDemo{

	//��ǰ�����˿˳�
	private List<Poker> deskPokerPool;
	
	//��ǰʣ���˿�
	private List<Poker> surplusPokerPool;
	
	//��Ϸ״̬ 0 δ��ʼ  1 ������
	private int gameStatus = 0;
	
	//��Ϸ���
	private int peopleNum = 0;
	//�˿�����
	private int pokerSet = 0;
	//�˿�����
	private int pokerNum = 0;

	
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
//			System.out.println("�˿��ƻ�ɫ��" + pokerPool.surplusPokerPool.get(i).color + " �����С��" + pokerPool.surplusPokerPool.get(i).point);
//		}
//		System.out.println("############ "  + pokerPool.peopleNum);
//		System.out.println("��������" + pokerPool.surplusPokerPool.size());
//		System.out.println("�����Ƴش�С��" + pokerPool.deskPokerPool.size());
		
//		Random random = new Random();
//		System.out.println((int)(Math.random()*2));
		
		char a = 'a';
		System.out.println((int)a);
		
	}
	/**
	 * ��ʼ����
	 */
	public List<Poker> startPork(){
		if(peopleNum != 0 && pokerSet != 0){
			//���Ƹ���Ϊ��Ϸ����+1������������Ϊ�Ƹ��� X 54
			for(int i = 0;i < peopleNum + 1;i++){
				Random random = new Random(pokerNum);
				int tempPoint = random.nextInt();
				Random color_Random = new Random(PokerVariable.COLOR_ALLNUM);
			}
		}
		return null;
	}
	/**
	 * ������Ϸ״̬
	 */
	public void resumes(){
		this.gameStatus = 0;
		surplusPokerPool = null;
		deskPokerPool = null;
	}
	
	/**
	 * ��ʣ���˿˳��������ȡһ���˿�
	 * @param site ����λ��
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
