package com.winegame.model;

import java.io.Serializable;


/**
 * 扑克模型
 * @author Michael
 *
 */
public class Poker implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int point;	//扑克点数
	
	public char color;	//扑克花色

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
