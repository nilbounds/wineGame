package com.winegame.model;

import java.io.Serializable;


/**
 * �˿�ģ��
 * @author Michael
 *
 */
public class Poker implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int point;	//�˿˵���
	
	public char color;	//�˿˻�ɫ

	public Poker(){
		super();
	}
	public Poker(int point,char color){
		this.point = point;
		this.color = color;
	}
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}
	
	
}
