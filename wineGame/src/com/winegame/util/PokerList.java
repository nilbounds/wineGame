package com.winegame.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.winegame.model.Poker;

public class PokerList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;
	
	private transient Poker[] elementData;

    private int size;
    
    
    
    public PokerList(int initialCapacity) {
    	super();
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal Capacity: "+
                                                   initialCapacity);
    	this.elementData = new Poker[initialCapacity];
    }

    public PokerList() {
    	this(10);
    }

	public PokerList(Collection<? extends E> c) {
    	elementData = (Poker[]) c.toArray();
    	size = elementData.length;
    	// c.toArray might (incorrectly) not return Object[] (see 6260652)
    	if (elementData instanceof Object[])
    	    elementData = (Poker[]) Arrays.copyOf(elementData, size, Object[].class);
    }



	public boolean remove(Poker poker){
		if(null != poker){
			for(int index = 0;index < size;index++){
				if(poker.point == elementData[index].point 
						&& poker.color == elementData[index].color){
					int numMoved = size - index - 1;
			        if (numMoved > 0)
			            System.arraycopy(elementData, index+1, elementData, index,
			                             numMoved);
			        elementData[--size] = null;
			        break;
				}
			}
		}
		return false;
	}
	
}
