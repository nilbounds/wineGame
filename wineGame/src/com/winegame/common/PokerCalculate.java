package com.winegame.common;

import com.winegame.model.Poker;


/**
 * �˿��Ƴ��Ƽ���
 * @author suny
 *
 */
public class PokerCalculate {
	
	/**
	 * ����
	 * @param site ����λ��
	 * @return 1 ը�� 2 ͬ��˳ 3 ˳�� 4 ͬ�� 5 ���� 6 ͨ��
	 */

	public static int checkWin(Poker desk,Poker last){
		System.out.println("�����ƻ�ɫ��" + desk.getColor() + "  ������" + desk.getPoint());
		System.out.println("���ƻ�ɫ��" + last.getColor() + "  ������" + last.getPoint());

		if(null != desk && null != last){
			//�жϻ�ɫ�Ƿ�һ��
			if(desk.getColor() == 'e' || desk.getColor() == 'f' || last.getColor() == 'e' || last.getColor() == 'f'){
				return 1;
			}
			int dValue = Math.abs(desk.getPoint() - last.getPoint());
			System.out.println("��ֵ��" + dValue);
			if(desk.getColor() != last.getColor()){
				if(dValue == 1 || dValue == 2 || dValue == 12 || dValue == 11){
					return 3;
				}else if(dValue == 0){
					return 1;
				}else{
					return 6;
				}
			}else{
				//�ж��Ƿ�ը��
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
